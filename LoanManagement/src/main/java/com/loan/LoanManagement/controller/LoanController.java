package com.loan.LoanManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.loan.LoanManagement.exception.InvalidSessionException;
import com.loan.LoanManagement.model.LoanDetails;
import com.loan.LoanManagement.service.LoanService;

@RestController
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping("get/{id}")
	public ResponseEntity<?> getCurrentData(@RequestHeader(name = "Authorization") String token, @PathVariable long id)
			throws InvalidSessionException {
		LoanDetails loan = loanService.getCurrentData(token, id);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}

	@PostMapping("/new")
	public ResponseEntity<?> addLoan(@RequestHeader(name = "Authorization") String token, @RequestBody LoanDetails loan)
			throws InvalidSessionException {
		LoanDetails loans = this.loanService.addLoanDetails(token, loan);
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}

	@PutMapping("edit/{id}")
	public ResponseEntity<?> updateLoan(@RequestHeader(name = "Authorization") String token, @PathVariable long id,
			@RequestBody LoanDetails loan) throws InvalidSessionException {
		loan.setLoanNumber(id);
		LoanDetails loan1 = this.loanService.updateLoan(token, loan);
		return new ResponseEntity<>(loan1, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteLoan(@RequestHeader(name = "Authorization") String token, @PathVariable long id)
			throws InvalidSessionException {
		this.loanService.deleteLoan(token, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
