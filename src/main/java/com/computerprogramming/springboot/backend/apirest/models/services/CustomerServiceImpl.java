package com.computerprogramming.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerprogramming.springboot.backend.apirest.models.dao.ICustomerDao;
import com.computerprogramming.springboot.backend.apirest.models.entity.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll()
		{
			return (List<Customer>) customerDao.findAll();
		}

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id)
		{
			return customerDao.findById(id).orElse(null);
		}

	@Override
	@Transactional
	public Customer save(Customer customer)
		{
			return customerDao.save(customer);
		}

	@Override
	@Transactional
	public void delete(Long id)
		{
			customerDao.deleteById(id);
		}

}
