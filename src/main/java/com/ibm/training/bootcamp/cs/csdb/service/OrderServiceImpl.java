package com.ibm.training.bootcamp.cs.csdb.service;

import java.util.List;


import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.cs.csdb.dao.OrderDao;
import com.ibm.training.bootcamp.cs.csdb.dao.OrderJdbcDaoImpl;
import com.ibm.training.bootcamp.cs.csdb.domain.Order;

public class OrderServiceImpl implements OrderService {
  
  OrderDao orderDao; 
  
  public OrderServiceImpl() {
    this.orderDao = OrderJdbcDaoImpl.getInstance();
    //this.userDao = UserHashMapDaoImpl.getInstance();
  }
  
  @Override
  public List<Order> findAll() {
    return orderDao.findAll();
  }

  @Override
  public Order find(Long order_id) {
    return orderDao.find(order_id);
  }

  @Override
  public List<Order> findByName(String cName) {
    return orderDao.findByName(cName);
  }

  @Override
  public void add(Order order) {
    if (validate(order)) {
      orderDao.add(order);
    } else {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
  }

  @Override
  public void upsert(Order order) {
    if (validate(order)) {
      if(order.getOrder_id() != null && order.getOrder_id() >= 0) {
        orderDao.update(order);
      } else {
        orderDao.add(order);
      }
    } else {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
  }

  
  private boolean validate(Order order) {
    return !StringUtils.isAnyBlank(order.getcName());
  }


}
