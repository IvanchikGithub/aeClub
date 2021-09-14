package com.aeClub.repository;

import org.springframework.data.repository.RepositoryDefinition;

import com.aeClub.entity.EmailPass;

@RepositoryDefinition(domainClass = EmailPass.class, idClass = Long.class)
public interface EmailPassRepository {
	EmailPass findByEmail(String email);
	void save(EmailPass emailPass);
	int countByEmail (String email);
	int countByIdUser (int idUser);
	
}
