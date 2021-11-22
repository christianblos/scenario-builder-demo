package com.example.demo.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

	public UUID getAuthenticatedPlayerId(HttpServletRequest request) {
		// JUST FOR DEMO PURPOSES!
		return UUID.fromString(request.getHeader("X-PLAYER"));
	}

}
