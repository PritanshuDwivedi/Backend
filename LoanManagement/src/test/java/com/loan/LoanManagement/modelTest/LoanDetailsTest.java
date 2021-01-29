package com.loan.LoanManagement.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.loan.LoanManagement.model.LoanDetails;

@SpringBootTest
public class LoanDetailsTest {

	@InjectMocks
	LoanDetails loanDetails;

	@Test
	public void getterSetterTest() {
		loanDetails.setLoanNumber(1);
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

		assertEquals(1, loanDetails.getLoanNumber());
		assertEquals("Pritanshu", loanDetails.getFName());
		assertEquals("Dwivedi", loanDetails.getLName());
		assertEquals("Pan", loanDetails.getLegalDocument());
		assertEquals("Yes", loanDetails.getLienInfo());
		assertEquals(12345, loanDetails.getLoanAmount());
		assertEquals("Good", loanDetails.getLoanHistory());
		assertEquals(234, loanDetails.getLoanManagementFees());
		assertEquals("Active", loanDetails.getLoanStatus());
		assertEquals(5, loanDetails.getLoanTerm());
		assertEquals("Home", loanDetails.getLoanType());
		assertEquals(new Date(25 / 04 / 2020), loanDetails.getOriginationDate());
		assertEquals("abcdef", loanDetails.getPropertyAddress());

	}

	@Test
	public void constructorTest() {
		loanDetails = new LoanDetails();

		assertNotNull(loanDetails);

		assertNotNull(new LoanDetails(1, "abcdef", "Home", 12345, 5, "Active", 234, new Date(25 / 04 / 2020), "Yes",
				"Pan", "Good", "Pritanshu", "Dwivedi"));

	}

}
