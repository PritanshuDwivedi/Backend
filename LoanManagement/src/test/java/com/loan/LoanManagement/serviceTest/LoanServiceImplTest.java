package com.loan.LoanManagement.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.loan.LoanManagement.exception.InvalidSessionException;
import com.loan.LoanManagement.exception.ResourceNotFoundException;
import com.loan.LoanManagement.model.AuthResponse;
import com.loan.LoanManagement.model.LoanDetails;
import com.loan.LoanManagement.proxy.AuthProxy;
import com.loan.LoanManagement.repository.LoanRepo;
import com.loan.LoanManagement.serviceImpl.LoanServiceImpl;

@SpringBootTest
public class LoanServiceImplTest {

	@InjectMocks
	LoanServiceImpl loanServiceImpl;

	@Mock
	LoanRepo loanRepo;

	@Mock
	LoanDetails loanDetails;

	@Mock
	private AuthProxy authProxy;

	@Mock
	AuthResponse authResponse;

	@BeforeEach
	void setup() {
		/*
		 * MockitoAnnotations.initMocks(this);
		 */
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
	public void getByIdTest() throws InvalidSessionException {

		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		long id = 1;
		when(loanRepo.findById(id)).thenReturn(Optional.of(loanDetails));
		LoanDetails result = loanServiceImpl.getCurrentData("token", id);
		assertEquals(1, result.getLoanNumber());
		assertEquals("Pritanshu", result.getFName());
		assertEquals("Dwivedi", result.getLName());
		assertEquals("Pan", result.getLegalDocument());
		assertEquals("Yes", result.getLienInfo());
		assertEquals(12345, result.getLoanAmount());
		assertEquals("Good", result.getLoanHistory());
		assertEquals(234, result.getLoanManagementFees());
		assertEquals("Active", result.getLoanStatus());
		assertEquals(5, result.getLoanTerm());
		assertEquals("Home", result.getLoanType());
		assertEquals(new Date(25 / 04 / 2020), result.getOriginationDate());
		assertEquals("abcdef", result.getPropertyAddress());
	}

	@Test
	public void getByIdExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		long id = 0;
		assertThrows(ResourceNotFoundException.class, () -> loanServiceImpl.getCurrentData("token", id));
	}

	@Test
	public void getByIdInvalidSessionExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(false);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		long id = 0;
		assertThrows(InvalidSessionException.class, () -> loanServiceImpl.getCurrentData("token", id));
	}

	@Test
	public void saveLoanTest() throws InvalidSessionException {

		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		when(loanRepo.save(loanDetails)).thenReturn(loanDetails);
		LoanDetails result = loanServiceImpl.addLoanDetails("token", loanDetails);
		assertEquals(1, result.getLoanNumber());
		assertEquals("Pritanshu", result.getFName());
		assertEquals("Dwivedi", result.getLName());
		assertEquals("Pan", result.getLegalDocument());
		assertEquals("Yes", result.getLienInfo());
		assertEquals(12345, result.getLoanAmount());
		assertEquals("Good", result.getLoanHistory());
		assertEquals(234, result.getLoanManagementFees());
		assertEquals("Active", result.getLoanStatus());
		assertEquals(5, result.getLoanTerm());
		assertEquals("Home", result.getLoanType());
		assertEquals(new Date(25 / 04 / 2020), result.getOriginationDate());
		assertEquals("abcdef", result.getPropertyAddress());

	}

	@Test
	public void saveInvalidSessionExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(false);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		assertThrows(InvalidSessionException.class, () -> loanServiceImpl.addLoanDetails("token", loanDetails));
	}

	@Test
	public void deleteTest() throws InvalidSessionException {

		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		when(loanRepo.findById(loanDetails.getLoanNumber())).thenReturn(Optional.of(loanDetails));
		loanServiceImpl.deleteLoan("token", loanDetails.getLoanNumber());
		verify(loanRepo).deleteById(loanDetails.getLoanNumber());
	}

	@Test
	public void deleteExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		long id = 0;
		assertThrows(ResourceNotFoundException.class, () -> loanServiceImpl.deleteLoan("token", id));
	}

	@Test
	public void deleteInvalidSessionExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(false);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		long id = 0;
		assertThrows(InvalidSessionException.class, () -> loanServiceImpl.deleteLoan("token", id));
	}

	@Test
	public void updateTest() throws InvalidSessionException {

		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);

		when(loanRepo.findById(loanDetails.getLoanNumber())).thenReturn(Optional.of(loanDetails));
		loanServiceImpl.updateLoan("token", loanDetails);
		loanDetails.setFName("P");
		loanRepo.save(loanDetails);
		LoanDetails updatedDetails = loanServiceImpl.getCurrentData("token", loanDetails.getLoanNumber());
		assertEquals("P", updatedDetails.getFName());

	}

	@Test
	public void updateExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(true);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		assertThrows(ResourceNotFoundException.class, () -> loanServiceImpl.updateLoan("token", loanDetails));
	}

	@Test
	public void updateInvalidExceptionTest() throws InvalidSessionException {
		authResponse = new AuthResponse();
		authResponse.setValid(false);
		when(authProxy.verifyToken("token")).thenReturn(authResponse);
		assertThrows(InvalidSessionException.class, () -> loanServiceImpl.updateLoan("token", loanDetails));
	}

}
