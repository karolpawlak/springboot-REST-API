package com.restapi.application;

import lombok.extern.slf4j.Slf4j;
import com.restapi.repository.*;
import com.restapi.entity.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repo1, CompanyRepository repo2, AddressRepository repo3, OrderRepository repo4) {
		return args -> {
			log.info("Preloading " + repo1.save(new Employee("Paul", "Pogba", "midfielder", "Elchamps", "Beau St.", "Paris","France","FR12")));
			log.info("Preloading " + repo1.save(new Employee("Marcus", "Rashford", "forward", "Bean House", "Victoria Street","Manchester","England","ENG14")));
			log.info("Preloading " + repo2.save(new Company("Dell", "City Gate", "Mahon", "Cork", "Cork", "FFF1")));
			log.info("Preloading " + repo2.save(new Company("VMware", "Building 6", "Main Street", "Ballincollig", "Cork", "VDG56")));
			log.info("Preloading " + repo3.save(new Address("1 EC", "Boreenmanna Road", "Cork", "Cork", "PWF6")));
			log.info("Preloading " + repo3.save(new Address("9 Bulgaden", "That Street 6", "Mallow", "Cork", "BGH2")));
			log.info("Preloading " + repo4.save(new Order("Macbook Pro", Status.IN_PROGRESS)));
			log.info("Preloading " + repo4.save(new Order("Dell XPS", Status.IN_PROGRESS)));
		};
	}
}
