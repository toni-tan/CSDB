package com.ibm.training.bootcamp.cs.csdb.dao;

import java.util.List;
import javax.servlet.http.HttpServlet;

import com.ibm.training.bootcamp.cs.csdb.domain.Order;
 
public interface OrderDao {
  
  public List<Order> findAll();
  
  public Order find(Long order_id);
  
  public List<Order> findByName(String cName);
  
  public void add(Order order);
  
  public void update(Order order);

}
