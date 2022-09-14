package com.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.order.model.JwtResponse;

@FeignClient(name="authorization-service",url="http://localhost:8084/api/auth")
public interface AuthFeign {
	@GetMapping("/validate")
	public JwtResponse verifyToken(@RequestHeader("Authorization") String token);

}

