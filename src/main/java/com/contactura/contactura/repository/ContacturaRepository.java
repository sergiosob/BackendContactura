package com.contactura.contactura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.contactura.contactura.model.Contactura;

@Repository
public interface ContacturaRepository extends JpaRepository<Contactura, Long> {
	
	@Query(value = "select * from Contact", nativeQuery = true)
	List<Contactura> findAll();
}
