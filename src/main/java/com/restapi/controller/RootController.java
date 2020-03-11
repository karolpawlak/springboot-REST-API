package com.restapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping
	public RepresentationModel index() {
		RepresentationModel rootModel = new RepresentationModel();
		rootModel.add(linkTo(methodOn(EmployeeController.class).all()).withRel("employee"));
		rootModel.add(linkTo(methodOn(CompanyController.class).all()).withRel("company"));
		rootModel.add(linkTo(methodOn(AddressController.class).all()).withRel("address"));
		rootModel.add(linkTo(methodOn(OrderController.class).all()).withRel("order"));
		return rootModel;
	}

}
