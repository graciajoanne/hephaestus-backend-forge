package com.fif.exercise2.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "loan_applications")
public class LoanApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //memiliki hubungan many to one dengan 
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "loan_amount", nullable = false)
    private BigDecimal loanAmount; //Karena nominal uang, tidak pakai double atau float karena bia ada error pembulatan

    @Column(name = "tenor_month", nullable = false)
    private Integer tenorMonth;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "loanApplication", fetch = FetchType.LAZY)
    private List<RepaymentScheduleEntity> repaymentSchedules;
}