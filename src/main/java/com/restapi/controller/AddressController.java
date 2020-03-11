package com.restapi.controller;

import com.restapi.entity.Address;
import com.restapi.handlers.*;
import com.restapi.repository.AddressRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
public class AddressController {

	@Autowired  
	private final AddressRepository repository;
	private final AddressModelAssembler assembler;

	public AddressController(AddressRepository repository, AddressModelAssembler assembler) {
		
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	@GetMapping("/address")
	public CollectionModel<EntityModel<Address>> all() {

		List<EntityModel<Address>> Addresss = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		return new CollectionModel<>(Addresss,
			linkTo(methodOn(AddressController.class).all()).withSelfRel());
	}

	@PostMapping("/address")
	public ResponseEntity<?> newAddress(@RequestBody Address newAddress) throws URISyntaxException {

		EntityModel<Address> entityModel = assembler.toModel(repository.save(newAddress));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	// Single item
	@GetMapping("/address/{id}")
	public EntityModel<Address> one(@PathVariable Long id) {

		Address Address = repository.findById(id)
			.orElseThrow(() -> new AddressNotFoundException(id));
		
		return assembler.toModel(Address);
	}

	@PutMapping("/address/{id}")
	ResponseEntity<?> replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) throws URISyntaxException {

		Address updatedAddress = repository.findById(id)
			.map(Address -> {
				Address.setLine1(newAddress.getLine1());
				Address.setLine2(newAddress.getLine2());
				Address.setCity(newAddress.getCity());
				Address.setCounty(newAddress.getCounty());
				Address.setPostcode(newAddress.getPostcode());
				return repository.save(Address);
			})
			.orElseGet(() -> {
				newAddress.setId(id);
				return repository.save(newAddress);
			});

		EntityModel<Address> entityModel = assembler.toModel(updatedAddress);

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	@DeleteMapping("/address/{id}")
	ResponseEntity<?> deleteAddress(@PathVariable Long id) {

		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
