Feature: Loan Details 

@Add 
Scenario: Add loan Detail 
	Given User is logged in and authorized 
	When User enters loan Details 
	Then Response Status Ok returned 
	
@Get 
Scenario: Get loan Detail 
	Given User is logged in and authorized 
	And Database has loan Details 
	When User enters loan Number 
	Then Response Status Ok returned 
	
@Update	
Scenario: Update loan Detail 
	Given User is logged in and authorized 
	And Database has loan Details 
	When User enters loan Number and update loan Details 
	Then Response Status Ok returned
	
@Delete 
Scenario: Delete loan Detail 
	Given User is logged in and authorized 
	And Database has loan Details 
	When User enters loan Number to delete loan Details 
	Then Response Status Ok returned 

 

	
	
