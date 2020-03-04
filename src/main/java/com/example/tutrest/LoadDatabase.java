package com.example.tutrest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository,CompanyRepository crepository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Paul", "Pogba", "midfielder", "Elchamps","Paris","France","FR12")));
			log.info("Preloading " + repository.save(new Employee("Marcus", "Rashford", "forward","Victoria Street","Manchester","England","ENG14")));
			log.info("Preloading " + crepository.save(new Company("Dell",40000)));
			log.info("Preloading " + crepository.save(new Company("VMware",20000)));
		};
	}
}
