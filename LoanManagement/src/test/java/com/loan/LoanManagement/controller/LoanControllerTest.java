package com.loan.LoanManagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.loan.LoanManagement.exception.InvalidSessionException;
import com.loan.LoanManagement.model.AuthResponse;
import com.loan.LoanManagement.model.LoanDetails;
import com.loan.LoanManagement.proxy.AuthProxy;
import com.loan.LoanManagement.repository.LoanRepo;
import com.loan.LoanManagement.service.LoanService;

@SpringBootTest
public class LoanControllerTest {

	@InjectMocks
	LoanController loanController;

	@Mock
	LoanRepo loanRepo;

	@Mock
	LoanService loanService;

	@Mock
	LoanDetails loanDetails;

	@Mock
	AuthResponse authResponse;

	@Mock
	AuthProxy authProxy;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();

		loanDetails = new LoanDetails();
		loanDetails.setLoanNumber((long) 1);
		loanDetails.setFName("Pritanshu");
		loanDetails.setLName("Dwivedi");
		loanDetails.setLegalDocument("Pan");
		loanDetails.setLienInfo("Yes");
		loanDetails.setLoanAmount(12345);
		loanDetails.setLoanHistory("Good");
		loanDetails.setLoanManagementFees(234);
		loanDetails.setLoanStatus("Active");
		loanDetails.setLoanTerm(5);
		loanDetails.setLoanType("Home");
		loanDetails.setOriginationDate(new Date(25 / 04 / 2020));
		loanDetails.setPropertyAddress("abcdef");
	}

	@Test
	public void getCurrentDataTest() throws InvalidSessionException {

		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		when(loanService.getCurrentData("token", 1)).thenReturn(loanDetails);
		ResponseEntity<?> loanCheck = loanController.getCurrentData("token", 1);
		assertEquals(200, loanCheck.getStatusCodeValue());
	}

	@Test
	public void deleteTest() throws Exception {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		when(loanRepo.findById(loanDetails.getLoanNumber())).thenReturn(Optional.of(loanDetails));
		ResponseEntity<?> loanCheck = loanController.deleteLoan("token", 1);
		assertEquals(200, loanCheck.getStatusCodeValue());

	}

	@Test
	public void addNewTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		when(loanService.addLoanDetails("token", loanDetails)).thenReturn(loanDetails);
		ResponseEntity<?> loanCheck = loanController.addLoan("token", loanDetails);
		assertEquals(200, loanCheck.getStatusCodeValue());

	}

	@Test
	public void updateTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		when(loanService.updateLoan("token", loanDetails)).thenReturn(loanDetails);
		loanDetails.setFName("P");
		when(loanRepo.save(loanDetails)).thenReturn(loanDetails);
		ResponseEntity<?> loanCheck = loanController.updateLoan("token", 1, loanDetails);
		assertEquals(200, loanCheck.getStatusCodeValue());

	}

}
