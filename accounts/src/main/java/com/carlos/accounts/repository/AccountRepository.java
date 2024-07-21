package com.carlos.accounts.repository;

import com.carlos.accounts.entity.Accounts;
import com.carlos.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     * @param customerId - id customer to delete
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
