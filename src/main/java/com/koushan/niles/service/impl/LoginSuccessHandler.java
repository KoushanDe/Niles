package com.koushan.niles.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException {

		UserServiceImpl userDetails = (UserServiceImpl) authentication.getPrincipal();
		
		String redirectURL = request.getContextPath();
		
		if (userDetails.hasRole("ROLE_ADMIN")) {
			redirectURL = "/admin";
		} else if (userDetails.hasRole("ROLE_USER")) {
			redirectURL = "/index";
		}
		
		response.sendRedirect(redirectURL);
		
	}
}
