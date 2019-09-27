package com.ibm.training.bootcamp.cs.csdb.domain;

import java.math.BigDecimal;

public class Food {
  Long id; 
  private String name;
  private BigDecimal price;
  private boolean inStock;
//  private Long order_id;
  
  public Food() {
  }
  
  public Food(String name, BigDecimal price, boolean inStock) {
    this(null, name, price, inStock);
//        order_id);
  }

  public Food(Long id, String name, BigDecimal price, boolean inStock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.inStock = inStock;
//    this.order_id = order_id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isInStock() {
    return inStock;
  }

  public void setInStock(boolean inStock) {
    this.inStock = inStock;
  }

//  public Long getOrder_id() {
//    return order_id;
//  }
//
//  public void setOrder_id(Long order_id) {
//    this.order_id = order_id;
//  }
//  
  
}
