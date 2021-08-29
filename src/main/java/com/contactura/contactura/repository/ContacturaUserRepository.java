package com.contactura.contactura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.contactura.contactura.model.ContacturaUser;

@Repository
public interface ContacturaUserRepository extends JpaRepository<ContacturaUser, Long> {

	@Query(value = "select * from User", nativeQuery = true)
	List<ContacturaUser> findAll();

	Object findByUsername(String username);

	
	
}