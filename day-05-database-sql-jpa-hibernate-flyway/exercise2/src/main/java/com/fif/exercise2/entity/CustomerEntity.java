package com.fif.exercise2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;


@Data
@Entity //anggap class ini sebagai tabel database
@Table(name = "customers") //entitas ini terhubunga ke tabel customer
@SQLRestriction("deleted_at IS NULL")  //
public class CustomerEntity {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID dibuat otomatis oleh database
    private Long id;

    @Column(name = "full_name", nullable = false) //nama kolom "full_name" tidak boleh kosong/null
    private String fullName;

    @Column(name = "nik", nullable = false, unique = true) // sama kaya kolom tapi harus unik
    private String nik;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) 
    // Satu customer bisa memiliki banyak pengajuan pinjaman (one to many)
    private List<LoanApplicationEntity> loanApplications;
}