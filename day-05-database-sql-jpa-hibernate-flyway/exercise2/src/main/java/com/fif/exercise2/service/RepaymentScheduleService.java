package com.fif.exercise2.service;

import com.fif.exercise2.dto.RepaymentScheduleResponse;
import com.fif.exercise2.entity.RepaymentScheduleEntity;
import com.fif.exercise2.exception.RepaymentScheduleNotFoundException;
import com.fif.exercise2.repository.RepaymentScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepaymentScheduleService {

    private final RepaymentScheduleRepository repaymentScheduleRepository;

    @Transactional(readOnly = true)
    public List<RepaymentScheduleResponse> getByLoanApplicationId(
            Long loanApplicationId, String status) {
        List<RepaymentScheduleEntity> result = status != null
            ? repaymentScheduleRepository.findByLoanApplicationIdAndStatus(loanApplicationId, status)
            : repaymentScheduleRepository.findByLoanApplicationId(loanApplicationId);
        return result.stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RepaymentScheduleResponse getById(Long id) {
        RepaymentScheduleEntity entity = repaymentScheduleRepository.findById(id)
            .orElseThrow(() -> new RepaymentScheduleNotFoundException(id));
        return buildResponse(entity);
    }

    private RepaymentScheduleResponse buildResponse(RepaymentScheduleEntity entity) {
        RepaymentScheduleResponse response = new RepaymentScheduleResponse();
        response.setId(entity.getId());
        response.setInstallmentNumber(entity.getInstallmentNumber());
        response.setDueDate(entity.getDueDate());
        response.setPrincipalAmount(entity.getPrincipalAmount());
        response.setInterestAmount(entity.getInterestAmount());
        response.setTotalAmount(entity.getTotalAmount());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}