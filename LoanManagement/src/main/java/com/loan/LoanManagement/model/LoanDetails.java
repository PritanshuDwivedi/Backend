package com.loan.LoanManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loanDetails")
//Used to store loan Details
public class LoanDetails {

	//column
	@Id
	@GeneratedValue
	@Column(name = "loanNumber")
	private long loanNumber;

	//column PropertyAddress
	@NotNull
	private String propertyAddress;
	
	//column
	@NotNull
	private String loanType;
	
	//column
	@NotNull
	private long loanAmount;
	
	//column
	@NotNull
	private long loanTerm;
	
	//column
	@NotNull
	private String loanStatus;
	
	//column
	@NotNull
	private long loanManagementFees;

	//column
	@NotNull
	private Date originationDate;

	//column
	@NotNull
	private String lienInfo;

	//column
	@NotNull
	private String legalDocument;

	//column
	@NotNull
	private String loanHistory;

	//column
	@NotNull
	private String fName;

	//column
	@NotNull
	private String lName;

}
