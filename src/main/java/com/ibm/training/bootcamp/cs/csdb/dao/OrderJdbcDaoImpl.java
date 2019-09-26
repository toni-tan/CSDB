package com.ibm.training.bootcamp.cs.csdb.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.cs.csdb.domain.Order;

public class OrderJdbcDaoImpl implements OrderDao {
  
  private static OrderJdbcDaoImpl INSTANCE;
  private JDBCDataSource dataSource;
  
  static public OrderJdbcDaoImpl getInstance() {
    
    OrderJdbcDaoImpl instance;
    if (INSTANCE != null) {
      instance = INSTANCE;
    } else {
      instance = new OrderJdbcDaoImpl();
      INSTANCE = instance;
    }

    return instance;
  }
  
  private OrderJdbcDaoImpl() {
    init();
  }
  
  private void init() {
    dataSource = new JDBCDataSource();
    dataSource.setDatabase("jdbc:hsqldb:mem:FOOD");
    dataSource.setUser("username");
    dataSource.setPassword("password");

    createOrderTable();
    //insertInitOrder();

  }

  private void createOrderTable() {
    String createSql = "CREATE TABLE ORDERS " + "(order_id INTEGER IDENTITY PRIMARY KEY, " + 
  " cName VARCHAR(255), " + " address VARCHAR(255), " + " contact VARCHAR(255), " + " total DECIMAL(9,2), " +
  " calculatedTotal DECIMAL(9,2), " + "status INTEGER, " + "quantity INTEGER," + "id INTEGER)";

    try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

      stmt.executeUpdate(createSql);

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void insertInitOrder() {
//    add(new Food("Taco", new BigDecimal(120.00), true));
//    add(new Food("Burrito", new BigDecimal(160.00), true));
  }

  @Override
  public List<Order> findAll() {

    return findByName(null);
  }

  @Override
  public Order find(Long order_id) {

    Order order = null;

    if (order_id != null) {
      String sql = "SELECT * FROM ORDERS where order_id = ?";
      try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, order_id.intValue());
        ResultSet results = ps.executeQuery();

        if (results.next()) {
          order = new Order(Long.valueOf(results.getInt("order_id")), results.getString("cName"), results.getString("address"),
             results.getString("contact"), results.getBigDecimal("total"), results.getBigDecimal("calculatedTotal"),
             results.getInt("status"), results.getInt("quantity"), Long.valueOf(results.getInt("id")));
        }

      } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }

    return order;
  }

  @Override
  public List<Order> findByName(String cName) {
    List<Order> order = new ArrayList<>();

    String sql = "SELECT * FROM ORDERS WHERE cName LIKE ?";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, createSearchValue(cName));
      
      ResultSet results = ps.executeQuery();

      while (results.next()) {
        Order o = new Order(Long.valueOf(results.getInt("order_id")), results.getString("cName"), results.getString("address"),
            results.getString("contact"), results.getBigDecimal("total"), results.getBigDecimal("calculatedTotal"),
            results.getInt("status"), results.getInt("quantity"), Long.valueOf(results.getInt("id")));
     order.add(o);    
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return order;
  }

  private String createSearchValue(String string) {
    
    String value;
    
    if (StringUtils.isBlank(string)) {
      value = "%";
    } else {
      value = string;
    }
      
    return value;
    
  }
  
  @Override
  public void add(Order order) {
    
    String insertSql = "INSERT INTO ORDERS (cName, address, contact, total, calculatedTotal, status, quantity, id) VALUES (?, ?. ?, ?, ?, ?, ?, ?)";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

      ps.setString(1, order.getcName());
      ps.setString(2, order.getAddress());
      ps.setString(3, order.getContact());
      ps.setBigDecimal(4, order.getTotal());
      ps.setBigDecimal(5, order.getCalculatedTotal());
      ps.setInt(6, order.getStatus());
      ps.setInt(7, order.getQuantity());
      ps.setLong(8, order.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Order order) {
    String updateSql = "UPDATE ORDERS SET cName = ?, address = ?, contact = ?, total = ?, calculatedTotal = ?, status = ?, quantity = ?, id = ? WHERE order_id = ?";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {
     
      ps.setString(1, order.getcName());
      ps.setString(2, order.getAddress());
      ps.setString(3, order.getContact());
      ps.setBigDecimal(4, order.getTotal());
      ps.setBigDecimal(5, order.getCalculatedTotal());
      ps.setInt(6, order.getStatus());
      ps.setInt(7, order.getQuantity());
      ps.setLong(8, order.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }



}
