package com.computerprogramming.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.computerprogramming.springboot.backend.apirest.models.entity.Customer;
import com.computerprogramming.springboot.backend.apirest.models.services.ICustomerService;

@CrossOrigin(origins =
	{ "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> index()
		{
			return customerService.findAll();
		}

	@GetMapping("/customers/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
		{
			Customer customer = null;
			Map<String, Object> response = new HashMap<>();
			try
				{
					customer = customerService.findById(id);
				} catch (DataAccessException dae)
				{
					response.put("message", "Error Database! ");
					response.put("error",
							dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			if (customer == null)
				{
					response.put("message", "ID Customer: ".concat(id.toString().concat(" does not exist!")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}

	@PostMapping("/customers")
	public ResponseEntity<?> create(@RequestBody Customer customer)
		{
			Customer newCustomer = null;
			Map<String, Object> response = new HashMap<>();

			try
				{
					newCustomer = customerService.save(customer);
				} catch (DataAccessException dae)
				{
					response.put("message", "Error create Customer Database! ");
					response.put("error",
							dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			response.put("message", " Customer CREATED!");
			response.put("customer", newCustomer);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		}

	@PutMapping("/customers/{id}")
	public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Long id)
		{
			Customer currentCustomer = customerService.findById(id);
			Customer updateCustomer = null;

			Map<String, Object> response = new HashMap<>();

			if (currentCustomer == null)
				{
					response.put("message", "ID Customer: ".concat(id.toString().concat(" does not exist!")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}

			try
				{
					currentCustomer.setName(customer.getName());
					currentCustomer.setLastName(customer.getLastName());
					currentCustomer.setEmail(customer.getEmail());
					currentCustomer.setCreateAt(customer.getCreateAt());

					updateCustomer = customerService.save(currentCustomer);

				} catch (DataAccessException dae)
				{
					response.put("message", "Error update Customer Database! ");
					response.put("error",
							dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			response.put("message", " Customer UPDATED!");
			response.put("customer", updateCustomer);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

	@DeleteMapping("/customers/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
		{
			Map<String, Object> response = new HashMap<>();
			try
				{
					customerService.delete(id);
				} catch (DataAccessException dae)
				{
					response.put("message", "Error delete Customer Database! ");
					response.put("error",
							dae.getMessage().concat(": ").concat(dae.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			response.put("message", "delete Customer!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
}
