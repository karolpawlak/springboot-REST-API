package com.restapi.handlers;

public class CompanyNotFoundException extends RuntimeException {

	public CompanyNotFoundException(Long id) {
		super("Could not find company " + id);
	}
}
