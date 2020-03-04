package com.example.tutrest;

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

// tag::constructor[]
@RestController
class CompanyController {

	@Autowired
	private final CompanyRepository repository;

	private final CompanyModelAssembler assembler;

	CompanyController(CompanyRepository repository,
					   CompanyModelAssembler assembler) {
		
		this.repository = repository;
		this.assembler = assembler;
	}
	// end::constructor[]

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/company")
	CollectionModel<EntityModel<Company>> all() {

		List<EntityModel<Company>> Companys = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		return new CollectionModel<>(Companys,
			linkTo(methodOn(CompanyController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	// tag::post[]
	@PostMapping("/company")
	ResponseEntity<?> newCompany(@RequestBody Company newCompany) throws URISyntaxException {

		EntityModel<Company> entityModel = assembler.toModel(repository.save(newCompany));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}
	// end::post[]

	// Single item

	// tag::get-single-item[]
	@GetMapping("/company/{id}")
	EntityModel<Company> one(@PathVariable Long id) {

		Company Company = repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		return assembler.toModel(Company);
	}
	// end::get-single-item[]

	// tag::put[]
	@PutMapping("/company/{id}")
	ResponseEntity<?> replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) throws URISyntaxException {

		Company updatedCompany = repository.findById(id)
			.map(Company -> {
				Company.setName(newCompany.getName());
				Company.setNoemployees(newCompany.getNoemployees());
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
	// end::put[]

	// tag::delete[]
	@DeleteMapping("/company/{id}")
	ResponseEntity<?> deleteCompany(@PathVariable Long id) {

		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	// end::delete[]
}
