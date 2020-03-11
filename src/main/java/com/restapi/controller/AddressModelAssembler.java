package com.restapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.restapi.entity.Address;


@Component
public class AddressModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {

	@Override
	public EntityModel<Address> toModel(Address Address) {

		return new EntityModel<>(Address,
			linkTo(methodOn(AddressController.class).one(Address.getId())).withSelfRel(),
			linkTo(methodOn(AddressController.class).all()).withRel("address"));
	}
}
