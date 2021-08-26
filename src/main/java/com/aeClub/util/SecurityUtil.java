package com.aeClub.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aeClub.entity.EmailPass;
import com.aeClub.model.CurrentProfile;

public final class SecurityUtil {
	
	public static CurrentProfile getCurrentProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentProfile) {
			return ((CurrentProfile) principal);
		} else {
			return null;
		}
	}

	public static Long getCurrentIdProfile() {
		CurrentProfile currentProfile = getCurrentProfile();
		return currentProfile != null ? currentProfile.getId() : null;
	}

	public static void authentificate(EmailPass emailPass) {
		CurrentProfile currentProfile = new CurrentProfile(emailPass);
		Authentication authentication = new UsernamePasswordAuthenticationToken(currentProfile,
				currentProfile.getPassword(), currentProfile.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static boolean isCurrentProfileAuthentificated() {
		return getCurrentProfile() != null;
	}
	
}
