package com.computerprogramming.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.computerprogramming.springboot.backend.apirest.models.entity.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long> {

}
