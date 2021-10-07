package com.aeClub.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aeClub.entity.EmailPass;

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

	public static int getCurrentIdProfile() {
		CurrentProfile currentProfile = getCurrentProfile();
		return currentProfile != null ? currentProfile.getId() : 0;
	}

	/**
	 * Dank diese Methode legen wir von Hand das aktuelles Objekt User in
	 * SpringSecurityContext, weil das in SpringSecurityContext nach der Erschaffung gelangen
	 * werden muss.
	 * <p>
	 * Anstatt User, verwenden wir eine eigene Klasse CurrentProfile, die die geerbte Klasse von User ist.
	 * Das Objekt von dieser Klasse CurrentProfile legen wir aus dem Objekt des EmailPasses an.
	 * 
	 * @param emailPass
	 * 
	 * @see CurrentProfile
	 */
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
