/**
 * 
 */
package br.com.mesquitas.projectcustomer.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.mesquitas.projectcustomer.entity.Customer;

/**
 * @author Alessandro Mesquita
 *
 */

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public Customer findByName(String name);

}
