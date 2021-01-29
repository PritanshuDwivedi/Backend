package com.loan.LoanManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.LoanManagement.model.LoanDetails;

@Repository
public interface LoanRepo extends JpaRepository<LoanDetails, Long> {

}
