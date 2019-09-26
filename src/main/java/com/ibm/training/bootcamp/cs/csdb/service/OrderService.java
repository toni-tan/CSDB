package com.ibm.training.bootcamp.cs.csdb.service;

import java.util.List;

import com.ibm.training.bootcamp.cs.csdb.domain.Order;

public interface OrderService {
  
public List<Order> findAll();
  
 public Order find(Long order_id);
 
 public List<Order> findByName(String cName);
  
 public void add(Order order);
  
 public void upsert(Order order);
 

}
