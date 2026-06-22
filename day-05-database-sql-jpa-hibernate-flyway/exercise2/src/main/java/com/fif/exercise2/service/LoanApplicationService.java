package com.fif.exercise2.service;

import com.fif.exercise2.dto.*;
import com.fif.exercise2.entity.CustomerEntity;
import com.fif.exercise2.entity.LoanApplicationEntity;
import com.fif.exercise2.entity.RepaymentScheduleEntity;
import com.fif.exercise2.exception.CustomerNotFoundException;
import com.fif.exercise2.exception.InvalidLoanStatusException;
import com.fif.exercise2.exception.LoanApplicationNotFoundException;
import com.fif.exercise2.repository.CustomerRepository;
import com.fif.exercise2.repository.LoanApplicationRepository;
import com.fif.exercise2.repository.RepaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;
    private final RepaymentScheduleRepository repaymentScheduleRepository;

    public LoanApplicationService(
            LoanApplicationRepository loanApplicationRepository,
            CustomerRepository customerRepository,
            RepaymentScheduleRepository repaymentScheduleRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.customerRepository = customerRepository;
        this.repaymentScheduleRepository = repaymentScheduleRepository;
    }

    @Value("${loan.interest.annual-rate}") /// @value tidak bisa bersamaan dengan @AllArgsConstructor sehingga contructor dibuat manual
    private double annualInterestRate;

    @Transactional
    public LoanApplicationResponse createLoanApplication(CreateLoanApplicationRequest request) {
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));

        LoanApplicationEntity entity = new LoanApplicationEntity();
        entity.setCustomer(customer);
        entity.setLoanAmount(request.getLoanAmount());
        entity.setTenorMonth(request.getTenorMonth());
        entity.setPurpose(request.getPurpose());
        entity.setStatus("SUBMITTED");
        entity.setCreatedAt(ZonedDateTime.now());
        entity.setUpdatedAt(ZonedDateTime.now());

        LoanApplicationEntity saved = loanApplicationRepository.save(entity);
        return buildResponse(saved);
    }

    @Transactional(readOnly = true)
    public LoanApplicationResponse getLoanApplicationById(Long id) {
        LoanApplicationEntity entity = loanApplicationRepository.findByIdWithCustomer(id)
            .orElseThrow(() -> new LoanApplicationNotFoundException(id));
        return buildResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getAllLoanApplications() {
        return loanApplicationRepository.findAll()
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getLoansByCustomerId(Long customerId) {
        return loanApplicationRepository.findLoansByCustomerId(customerId)
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getLoansByStatus(String status) {
        return loanApplicationRepository.findByStatus(status)
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<LoanApplicationResponse> getAllLoanApplicationsPaged(
            int page, int size, String status) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<LoanApplicationEntity> result = status != null
            ? loanApplicationRepository.findByStatus(status, pageable)
            : loanApplicationRepository.findAll(pageable);
        return result.map(this::buildResponse);
    }

    @Transactional
    public LoanApplicationResponse updateLoanStatus(Long id, UpdateLoanStatusRequest request) {
        LoanApplicationEntity entity = loanApplicationRepository.findById(id)
            .orElseThrow(() -> new LoanApplicationNotFoundException(id));

        // Validasi status flow
        validateStatusTransition(entity.getStatus(), request.getStatus());

        entity.setStatus(request.getStatus());
        entity.setUpdatedAt(ZonedDateTime.now());

        // Generate repayment schedule hanya saat DISBURSED
        if (request.getStatus().equals("DISBURSED")) {
            generateRepaymentSchedules(entity);
        }

        LoanApplicationEntity saved = loanApplicationRepository.save(entity);
        return buildResponse(saved);
    }

    private void validateStatusTransition(String currentStatus, String newStatus) {
        // Status akhir tidak bisa diubah
        if (currentStatus.equals("REJECTED") || currentStatus.equals("CLOSED")) {
            throw new InvalidLoanStatusException(
                "Loan with status " + currentStatus + " cannot be changed");
        }

        // Validasi urutan status
        boolean valid = switch (currentStatus) {
            case "SUBMITTED" -> newStatus.equals("APPROVED") || newStatus.equals("REJECTED");
            case "APPROVED"  -> newStatus.equals("DISBURSED");
            case "DISBURSED" -> newStatus.equals("CLOSED");
            default -> false;
        };

        if (!valid) {
            throw new InvalidLoanStatusException(
                "Cannot change status from " + currentStatus + " to " + newStatus);
        }
    }

    private void generateRepaymentSchedules(LoanApplicationEntity loan) {
        // Hitung bunga dari config
        BigDecimal monthlyRate = BigDecimal.valueOf(annualInterestRate / 12);

        BigDecimal principal = loan.getLoanAmount()
            .divide(BigDecimal.valueOf(loan.getTenorMonth()), 0, RoundingMode.HALF_UP);

        BigDecimal interest = loan.getLoanAmount()
            .multiply(monthlyRate)
            .setScale(0, RoundingMode.HALF_UP);

        BigDecimal total = principal.add(interest);

        List<RepaymentScheduleEntity> schedules = new ArrayList<>();
        for (int i = 1; i <= loan.getTenorMonth(); i++) {
            RepaymentScheduleEntity schedule = new RepaymentScheduleEntity();
            schedule.setLoanApplication(loan);
            schedule.setInstallmentNumber(i);
            schedule.setDueDate(LocalDate.now().plusMonths(i));
            schedule.setPrincipalAmount(principal);
            schedule.setInterestAmount(interest);
            schedule.setTotalAmount(total);
            schedule.setStatus("UNPAID");
            schedule.setCreatedAt(ZonedDateTime.now());
            schedule.setUpdatedAt(ZonedDateTime.now());
            schedules.add(schedule);
        }
        repaymentScheduleRepository.saveAll(schedules);
    }

    private LoanApplicationResponse buildResponse(LoanApplicationEntity entity) {
        LoanApplicationResponse response = new LoanApplicationResponse();
        response.setId(entity.getId());
        response.setLoanAmount(entity.getLoanAmount());
        response.setTenorMonth(entity.getTenorMonth());
        response.setPurpose(entity.getPurpose());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getCustomer() != null) {
            CustomerSummaryResponse customerSummary = new CustomerSummaryResponse();
            customerSummary.setId(entity.getCustomer().getId());
            customerSummary.setFullName(entity.getCustomer().getFullName());
            customerSummary.setNik(entity.getCustomer().getNik());
            customerSummary.setEmail(entity.getCustomer().getEmail());
            response.setCustomer(customerSummary);
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getLoansByDate(LocalDate date) {
        return loanApplicationRepository.findByCreatedAtDate(date)
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getLoansByDateRange(
            ZonedDateTime start, ZonedDateTime end) {
        return loanApplicationRepository.findByCreatedAtBetween(start, end)
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanSummaryResponse> getLoanSummaryByStatus() {
        List<Object[]> results = loanApplicationRepository.getSummaryByStatus();
        List<LoanSummaryResponse> summaries = new ArrayList<>();
        for (Object[] row : results) {
            summaries.add(new LoanSummaryResponse(
                (String) row[0],
                ((Number) row[1]).longValue(),
                new BigDecimal(row[2].toString())
            ));
        }
        return summaries;
    }
}