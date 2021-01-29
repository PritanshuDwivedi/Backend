
package com.loan.LoanManagement.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.loan.LoanManagement.model.AuthResponse;

@FeignClient(name = "${feign.auth.name}", url = "${feign.auth.url}")
public interface AuthProxy {

	@GetMapping("/validate")
	public AuthResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
