/**
 * 
 */
package br.com.mesquitas.projectcustomer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mesquitas.projectcustomer.entity.Customer;
import br.com.mesquitas.projectcustomer.repository.CustomerRepository;

/**
 * @author Alessandro Mesquita
 *
 */

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	public CustomerController(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}

	@PostMapping
	@ResponseBody
	public Customer create(@RequestParam String name, @RequestParam Integer age) {
		Customer customer = new Customer(name, age);

		if (name != null && name.length() > 0 && age != null && age > 0) {
			repository.save(customer);
		}
		return customer;
	}

	@GetMapping
	@ResponseBody
	public Iterable<Customer> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	public Optional<Customer> findById(@PathVariable Long id) {
		return repository.findById(id);
	}

	@GetMapping(path = "/name/{name}")
	@ResponseBody
	public Customer findbyName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public void destroy(@PathVariable Long id) {

		Optional<Customer> customer = repository.findById(id);

		if (customer != null) {
			repository.deleteById(id);
		}

	}

	@PutMapping(path = "/{id}")
	@ResponseBody
	public Customer update(@PathVariable Long id, Integer age, String name) {

		Optional<Customer> obj = repository.findById(id);

		Customer customer = new Customer();

		customer.setId(obj.get().getId());
		customer.setName(name);
		customer.setAge(age);

		repository.save(customer);

		return customer;

	}

}
