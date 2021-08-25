package com.aeClub.model;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.aeClub.Constants;
import com.aeClub.entity.EmailPass;

public class CurrentProfile extends User {
	private static final long serialVersionUID = 3850489832510630519L;
	private final Long id;

	public CurrentProfile(EmailPass loginPass) {
		super(loginPass.getEmail(), loginPass.getPassword(), true, true, true, true, Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
		this.id = loginPass.getId();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("CurrentProfile [id=%s, username=%s]", id, getUsername());
	}
}
