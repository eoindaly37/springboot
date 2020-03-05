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
class AddressController {

	@Autowired  
	private final AddressRepository repository;

	private final AddressModelAssembler assembler;

	AddressController(AddressRepository repository,
					   AddressModelAssembler assembler) {
		
		this.repository = repository;
		this.assembler = assembler;
	}
	// end::constructor[]

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/address")
	CollectionModel<EntityModel<Address>> all() {

		List<EntityModel<Address>> Addresss = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		return new CollectionModel<>(Addresss,
			linkTo(methodOn(AddressController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	// tag::post[]
	@PostMapping("/address")
	ResponseEntity<?> newAddress(@RequestBody Address newAddress) throws URISyntaxException {

		EntityModel<Address> entityModel = assembler.toModel(repository.save(newAddress));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}
	// end::post[]

	// Single item

	// tag::get-single-item[]
	@GetMapping("/address/{id}")
	EntityModel<Address> one(@PathVariable Long id) {

		Address Address = repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		return assembler.toModel(Address);
	}
	// end::get-single-item[]

	// tag::put[]
	@PutMapping("/address/{id}")
	ResponseEntity<?> replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) throws URISyntaxException {

		Address updatedAddress = repository.findById(id)
			.map(Address -> {
				Address.setCity(newAddress.getCity());
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
	// end::put[]

	// tag::delete[]
	@DeleteMapping("/address/{id}")
	ResponseEntity<?> deleteAddress(@PathVariable Long id) {

		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	// end::delete[]
}
