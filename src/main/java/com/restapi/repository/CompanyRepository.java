package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> 
{

}