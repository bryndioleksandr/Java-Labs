package org.example.lab_11.repository;


import jakarta.transaction.Transactional;
import org.example.lab_11.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.bonus = :bonus WHERE c.id = :id")
    int updateCustomerBonus(@Param("id") Long id, @Param("bonus") double bonus);

    @Query("FROM Customer c WHERE c.surname = :surname")
    List<Customer> findBySurname(@Param("surname") String surname);
}
