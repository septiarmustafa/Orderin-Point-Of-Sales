package com.enigma.orderin.repository;

import com.enigma.orderin.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Modifying
    @Query(value = "INSERT INTO m_admin (name, phone_number) VALUES (?1, ?2)", nativeQuery = true)
    void createAdmin(String name, String phoneNumber);

    @Query(value = "SELECT * FROM m_admin", nativeQuery = true)
    List<Admin> getAllAdmin();

    @Query(value = "SELECT * FROM m_admin WHERE id = ?1", nativeQuery = true)
    Optional<Admin> getAdminById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_admin SET name = ?1, phone_number = ?2 WHERE id = ?3", nativeQuery = true)
    void updateAdmin(String name, String phoneNumber, Integer id);
}
