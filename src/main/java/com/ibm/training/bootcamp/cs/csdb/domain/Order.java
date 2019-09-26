package com.ibm.training.bootcamp.cs.csdb.domain;

import java.math.BigDecimal;

public class Order {
  Long order_id;
  private String cName;
  private String address;
  private String contact;
  private BigDecimal total;
  private BigDecimal calculatedTotal;
  private int status;
  private int quantity;
  private Long id;
  
  public Order() {
    
  }
  
  public Order(String cName, String address, String contact, BigDecimal total,
      BigDecimal calculatedTotal, int status, int quantity, Long id) {
    this(null, cName, address, contact, total, calculatedTotal, status,quantity,id);
  }

  public Order(Long order_id, String cName, String address, String contact,
      BigDecimal total, BigDecimal calculatedTotal, int status, int quantity,
      Long id) {
    this.order_id = order_id;
    this.cName = cName;
    this.address = address;
    this.contact = contact;
    this.total = total;
    this.calculatedTotal = calculatedTotal;
    this.status = status;
    this.quantity = quantity; 
    this.id = id;    
  }

  public Long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(Long order_id) {
    this.order_id = order_id;
  }

  public String getcName() {
    return cName;
  }

  public void setcName(String cName) {
    this.cName = cName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getCalculatedTotal() {
    return calculatedTotal;
  }

  public void setCalculatedTotal(BigDecimal calculatedTotal) {
    this.calculatedTotal = calculatedTotal;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  
}
