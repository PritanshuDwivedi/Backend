package com.loan.LoanManagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loan.LoanManagement.exception.InvalidSessionException;
import com.loan.LoanManagement.exception.ResourceNotFoundException;
import com.loan.LoanManagement.model.LoanDetails;
import com.loan.LoanManagement.proxy.AuthProxy;
import com.loan.LoanManagement.repository.LoanRepo;
import com.loan.LoanManagement.service.LoanService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LoanServiceImpl implements LoanService {

	@Autowired
	private AuthProxy authProxy;

	@Autowired
	private LoanRepo loanRepo;

	@Override
	public LoanDetails getCurrentData(String token, long id) throws InvalidSessionException {
		if (!authProxy.verifyToken(token).isValid()) {
			log.error("Authroization Error: User Not Authorized");
			throw new InvalidSessionException("Invalid Session");
		}
		Optional<LoanDetails> loanDb = this.loanRepo.findById(id);
		if (loanDb.isPresent()) {
			return loanDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found");
		}
	}

	@Override
	public LoanDetails updateLoan(String token, LoanDetails loan) throws InvalidSessionException {
		if (!authProxy.verifyToken(token).isValid()) {
			log.error("Authroization Error: User Not Authorized");
			throw new InvalidSessionException("Invalid Session");
		}
		Optional<LoanDetails> loanDb = this.loanRepo.findById(loan.getLoanNumber());
		if (loanDb.isPresent()) {
			LoanDetails loanUpdate = loanDb.get();
			loanUpdate.setFName(loan.getFName());
			loanUpdate.setLName(loan.getLName());
			loanUpdate.setLoanAmount(loan.getLoanAmount());
			loanUpdate.setLegalDocument(loan.getLegalDocument());
			loanUpdate.setLienInfo(loan.getLienInfo());
			loanUpdate.setLoanHistory(loan.getLoanHistory());
			loanUpdate.setLoanManagementFees(loan.getLoanManagementFees());
			loanUpdate.setLoanTerm(loan.getLoanTerm());
			loanUpdate.setLoanStatus(loan.getLoanStatus());
			loanUpdate.setOriginationDate(loan.getOriginationDate());
			loanUpdate.setPropertyAddress(loan.getPropertyAddress());
			loanUpdate.setLoanType(loan.getLoanType());
			loanRepo.save(loanUpdate);
			return loanUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found");
		}
	}

	@Override
	public void deleteLoan(String token, long id) throws InvalidSessionException {
		if (!authProxy.verifyToken(token).isValid()) {
			log.error("Authroization Error: User Not Authorized");
			throw new InvalidSessionException("Invalid Session");
		}
		Optional<LoanDetails> loanDb = this.loanRepo.findById(id);
		if (loanDb.isPresent()) {
			loanRepo.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Record not found");
		}

	}

	@Override
	public LoanDetails addLoanDetails(String token, LoanDetails loan) throws InvalidSessionException {
		if (!authProxy.verifyToken(token).isValid()) {
			log.error("Authroization Error: User Not Authorized");
			throw new InvalidSessionException("Invalid Session");
		}
		return loanRepo.save(loan);
	}

}
