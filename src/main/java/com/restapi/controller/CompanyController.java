package com.restapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.restapi.entity.Company;
import com.restapi.handlers.*;
import com.restapi.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

	@Autowired  
	private final CompanyRepository repository;
	private final CompanyModelAssembler assembler;

	public CompanyController(CompanyRepository repository,
					   CompanyModelAssembler assembler) {
		
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	@GetMapping("/company")
	public CollectionModel<EntityModel<Company>> all() {

		List<EntityModel<Company>> Companys = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		return new CollectionModel<>(Companys,
			linkTo(methodOn(CompanyController.class).all()).withSelfRel());
	}

	@PostMapping("/company")
	public ResponseEntity<?> newCompany(@RequestBody Company newCompany) throws URISyntaxException {

		EntityModel<Company> entityModel = assembler.toModel(repository.save(newCompany));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	// Single item
	@GetMapping("/company/{id}")
	public EntityModel<Company> one(@PathVariable Long id) {

		Company Company = repository.findById(id)
			.orElseThrow(() -> new CompanyNotFoundException(id));
		
		return assembler.toModel(Company);
	}

	@PutMapping("/company/{id}")
	public ResponseEntity<?> replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) throws URISyntaxException {

		Company updatedCompany = repository.findById(id)
			.map(Company -> {
				Company.setName(newCompany.getName());
				return repository.save(Company);
			})
			.orElseGet(() -> {
				newCompany.setId(id);
				return repository.save(newCompany);
			});

		EntityModel<Company> entityModel = assembler.toModel(updatedCompany);

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long id) {

		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
