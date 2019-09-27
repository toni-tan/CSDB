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

import com.ibm.training.bootcamp.cs.csdb.domain.Food;
public class FoodJdbcDaoImpl implements FoodDao{
  
  private static FoodJdbcDaoImpl INSTANCE;
  private JDBCDataSource dataSource;

  static public FoodJdbcDaoImpl getInstance() {

    FoodJdbcDaoImpl instance;
    if (INSTANCE != null) {
      instance = INSTANCE;
    } else {
      instance = new FoodJdbcDaoImpl();
      INSTANCE = instance;
    }

    return instance;
  }

  private FoodJdbcDaoImpl() {
    init();
  }

  private void init() {
    dataSource = new JDBCDataSource();
    dataSource.setDatabase("jdbc:hsqldb:mem:FOOD");
    dataSource.setUser("username");
    dataSource.setPassword("password");

    createFoodTable();
    insertInitFood();

  }

  private void createFoodTable() {
//    String dropSql = "DROP TABLE PUBLIC.FOOD";
    
    String createSql = "CREATE TABLE FOOD " + "(id INTEGER IDENTITY PRIMARY KEY, " + 
  " name VARCHAR(255), " + " price DECIMAL(9,2), "  + "inStock BOOLEAN) ";
   
//    String alterSql = "ALTER TABLE food ADD FOREIGN KEY(order_id) REFERENCES orders(order_id)";

    try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
      
//      stmt.executeUpdate(dropSql);
      stmt.executeUpdate(createSql);
//      stmt.executeUpdate(alterSql);

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void insertInitFood() {
//    add(new Food("Taco", new BigDecimal(120.00), true));
//    add(new Food("Burrito", new BigDecimal(160.00), true));
  }

  @Override
  public List<Food> findAll() {

    return findByName(null);
  }

  @Override
  public Food find(Long id) {

    Food food = null;

    if (id != null) {
      String sql = "SELECT * FROM FOOD where id = ?";
      try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id.intValue());
        ResultSet results = ps.executeQuery();

        if (results.next()) {
          food = new Food(Long.valueOf(results.getInt("id")), results.getString("name"), results.getBigDecimal("price"),
              results.getBoolean("inStock"));
        }

      } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }

    return food;
  }

  @Override
  public List<Food> findByName(String name) {
    List<Food> food = new ArrayList<>();

    String sql = "SELECT * FROM FOOD WHERE name LIKE ?";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, createSearchValue(name));
      
      ResultSet results = ps.executeQuery();

      while (results.next()) {
        Food f = new Food(Long.valueOf(results.getInt("id")), results.getString("name"), results.getBigDecimal("price"),
            results.getBoolean("inStock"));
        food.add(f);    
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return food;
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
  public boolean add(Food food) {
    boolean ifFoodNameExists = findExisting(food.getName());
    
    if (ifFoodNameExists == false) {
    
    String insertSql = "INSERT INTO FOOD (name, price, inStock) VALUES (?, ?, ?)";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

      ps.setString(1, food.getName());
      ps.setBigDecimal(2, food.getPrice());
      ps.setBoolean(3, food.isInStock());
//      ps.setLong(4, food.getOrder_id());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    }
    
    return ifFoodNameExists;
  }
  
  @Override 
  public boolean findExisting(String name) {
    boolean ifFoodNameExists = false;
    
    if(name != null) {
      String sql = "SELECT * FROM FOOD WHERE name LIKE ?";

      try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, createSearchValue(name));
        
        ResultSet results = ps.executeQuery();

        if (results.next()) {
//          Food f = new Food(Long.valueOf(results.getInt("id")), results.getString("name"), results.getBigDecimal("price"),
//              results.getBoolean("inStock"));
//          food.add(f);    
          ifFoodNameExists = true;
        }

      } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
    return ifFoodNameExists;

      
    }
  

  @Override
  public void update(Food food) {
    String updateSql = "UPDATE FOOD SET name = ?, price = ?, inStock = ? WHERE id = ?";

    try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {
     
      ps.setString(1, food.getName());
      ps.setBigDecimal(2, food.getPrice());
      ps.setBoolean(3, food.isInStock());
//      ps.setLong(4, food.getOrder_id());
      ps.setLong(4, food.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
