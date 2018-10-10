package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import entity.Customer;
import util.DBCon;

public class CustomerDaoImpl implements CustomerDao {
	
	DBCon connection = new DBCon();

	@Override
	public boolean add(Customer customer) {
		// TODO Auto-generated method stub
		
		String sql = "insert into customer(CustomerId, Name, Birth, Address) values (?,?,?,?)";

		return connection.update(sql, customer.getCustomerId(),customer.getName(),customer.getBirth(),customer.getAddress())>0;
	}

	@Override
	public boolean delete(int CustomerId) {
		// TODO Auto-generated method stub
		String sql = "delete from customer where CustomerId = ?";
		return connection.update(sql, CustomerId)>0;
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "update customer set Name = ?, Birth = ?, Address = ? where CustomerId=?";
		return connection.update(sql, customer.getName(),customer.getBirth(),customer.getAddress(), customer.getCustomerId())>0;
	}

	@Override
	public Customer queryById(int CustomerId) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where CustomerId = ?";
		return _customer(connection.query(sql, CustomerId));
	}

	@Override
	public List<Customer> queryAll() {
		// TODO Auto-generated method stub
		String sql="select * from customer";
		return _list(connection.query(sql));
	}
	
	private Customer _customer(ResultSet rs) {

		Customer customer = null;
		
			try {
				if(rs.next()) {
					
				customer = new Customer();
				
				customer.setCustomerId(rs.getInt("CustomerId"));
				customer.setName(rs.getString("Name"));
				customer.setBirth(rs.getDate("Birth"));
				customer.setAddress(rs.getString("Address"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				connection.closeAll();
			}
		return customer;
	}

	private List<Customer> _list(ResultSet rs){
		List<Customer> listcus= null;
		listcus = new ArrayList<Customer>();
		Customer customer=null;
		try {
			while(rs.next()) {
			    customer = new Customer();
			    customer.setCustomerId(rs.getInt("CustomerId"));
			    customer.setName(rs.getString("Name"));
			    customer.setAddress(rs.getString("Address"));
			    listcus.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.closeAll();
		}
	     return listcus;
	}
}
