package com.aeClub.repository;

import org.springframework.data.repository.RepositoryDefinition;

import com.aeClub.entity.Account;

@RepositoryDefinition(domainClass = Account.class, idClass = int.class)
public interface AccountRepository {
	void save(Account account);
	int countByIdUser (int idUser);
	Account findByIdUser (int idUser);
	void removeAll();
	
}
