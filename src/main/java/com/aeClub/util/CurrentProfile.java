package com.aeClub.util;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.aeClub.Constants;
import com.aeClub.entity.EmailPass;

public class CurrentProfile extends User {
	private static final long serialVersionUID = 3850489832510630519L;
	private final int id;
	

	public CurrentProfile(EmailPass emailPass) {
		super(emailPass.getEmail(), emailPass.getPassword(), true, true, true, true,
				Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
		this.id = emailPass.getIdUser();
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("CurrentProfile [id=%s, username=%s]", id, getUsername());
	}
}
