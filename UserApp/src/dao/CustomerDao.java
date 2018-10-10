package dao;

import java.util.List;

import entity.Customer;

public interface CustomerDao {
	
	boolean add(Customer customer);
	
	boolean delete(int CustomerId);
	
	boolean update(Customer customer);
	
	Customer queryById(int CustomerId);
	
	List<Customer> queryAll();

}
