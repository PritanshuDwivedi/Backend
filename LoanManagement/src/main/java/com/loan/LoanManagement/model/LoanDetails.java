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
public class LoanDetails {

	@Id
	@GeneratedValue
	@Column(name = "loanNumber")
	private long loanNumber;

	@NotNull
	private String propertyAddress;

	@NotNull
	private String loanType;

	@NotNull
	private long loanAmount;

	@NotNull
	private long loanTerm;

	@NotNull
	private String loanStatus;

	@NotNull
	private long loanManagementFees;

	@NotNull
	private Date originationDate;

	@NotNull
	private String lienInfo;

	@NotNull
	private String legalDocument;

	@NotNull
	private String loanHistory;

	@NotNull
	private String fName;

	@NotNull
	private String lName;

}
