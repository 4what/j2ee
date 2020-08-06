package com.example.service;

import com.example.dao.GenericRepository;
import com.example.domain.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	@Autowired
	private GenericRepository genericRepository;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	public Admin getCurrentUser() {
		Admin result = null;

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		for (Admin admin : genericRepository.list(Admin.class, "WHERE username = ?", userDetails.getUsername())) {
			result = admin;
		}

		return result;
	}
}
