package com.fif.exercise2.repository;

import com.fif.exercise2.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    //Optional karena bisa jadi customer tidak ditemukan.
    Optional<CustomerEntity> findByNik(String nik);

    Optional<CustomerEntity> findByEmail(String email);
    //Dipakai untuk validasi duplicate
    boolean existsByNik(String nik);

    boolean existsByEmail(String email);
    // mencari semua sesuai search dengan mengabaikan kapital seperti LIKE %...%
    List<CustomerEntity> findByFullNameContainingIgnoreCase(String fullName);
}