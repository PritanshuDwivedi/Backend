Feature: Authentication Microservice 

@Login 
Scenario: User Should be able to Login With Valid credentials 
	Given Post Login 
	When Provide Valid Credential with username "user" and password "pass" 
	Then Status_code equals "200" 
	
@Login 
Scenario: User Should not be able to Login With inValid Scredentials 
	Given Post Login 
	When Provide Valid Credential with username "user" and password "p" 
	Then Status_code equals "404" 
	
@Valid 
Scenario: User Should be validated 
	Given Post Login 
	When Provide Valid token 
	Then Status_code equals "200" 
	
@Valid 
Scenario: User is inValid 
	Given Post Login 
	When Provide inValid token 
	Then Status_code equals "403" 
	
	
