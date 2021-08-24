package com.contactura.contactura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactura.contactura.model.ContacturaUser;
import com.contactura.contactura.repository.ContacturaUserRepository;
import com.contactura.contactura.service.Mensagem;

@RestController
@RequestMapping({"/user"}) 
public class ContacturaControllerUser {
	
	@Autowired
	private ContacturaUserRepository repository;

	// List All - http://localhost:8090/contacturauser
	@GetMapping
	public List<ContacturaUser> findAll() {
		return repository.findAll();
	}
	
// Find By Id - http://localhost:8095/contactura/{id}
	@GetMapping(value = "{id]")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
		
// Create -  http://localhost:8095/contactura/
	@PostMapping
	public ContacturaUser create(@RequestBody ContacturaUser user) {
		return repository.save(user);
		
	}
	
// Update - http://localhost:8095/contactura/{id}
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestBody ContacturaUser user){
			return repository.findById(id)
				.map(record -> {
					record.setUsername(user.getUsername());
					record.setPassword(user.getPassword());
					record.setName(user.getName());
					record.setAdmin(true);
					ContacturaUser update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
//Delete - http://localhost:8095/contactura/{id}
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			Mensagem mensagem = new Mensagem("Deletado com sucesso.");
			return ResponseEntity.ok().body(mensagem);
		}).orElse(ResponseEntity.notFound().build());
	};

	}