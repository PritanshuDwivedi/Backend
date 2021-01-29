package com.loan.LoanManagement.service;

import com.loan.LoanManagement.exception.InvalidSessionException;
import com.loan.LoanManagement.model.LoanDetails;

public interface LoanService {

	public LoanDetails getCurrentData(String token, long id) throws InvalidSessionException;

	public LoanDetails updateLoan(String token, LoanDetails loan) throws InvalidSessionException;

	public void deleteLoan(String token, long id) throws InvalidSessionException;

	LoanDetails addLoanDetails(String token, LoanDetails loan) throws InvalidSessionException;
}
