package com.restapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.restapi.entity.Company;

@Component
public class CompanyModelAssembler implements RepresentationModelAssembler<Company, EntityModel<Company>> {

	@Override
	public EntityModel<Company> toModel(Company company) 
	{

		return new EntityModel<>(company,
			linkTo(methodOn(CompanyController.class).one(company.getId())).withSelfRel(),
			linkTo(methodOn(CompanyController.class).all()).withRel("company"));
	}
}
