package com.enigma.orderin.repository;

import com.enigma.orderin.entity.Cashier;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Integer> {
    @Modifying
    @Query(value = "INSERT INTO m_cashier (name, phone_number) VALUES (?1, ?2)", nativeQuery = true)
    void createCashier(String name, String phoneNumber);

    @Query(value = "SELECT * FROM m_cashier", nativeQuery = true)
    List<Cashier> getAllCashier();

    @Query(value = "SELECT * FROM m_cashier WHERE id = ?1", nativeQuery = true)
    Optional<Cashier> getCashierById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_cashier SET name = ?1, phone_number = ?2 WHERE id = ?3", nativeQuery = true)
    void updateCashier(String name, String phoneNumber, Integer id);
}
