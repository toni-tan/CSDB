package com.ibm.training.bootcamp.cs.csdb.dao;

import java.util.List;
import javax.servlet.http.HttpServlet;

import com.ibm.training.bootcamp.cs.csdb.domain.Food;
 

public interface FoodDao {
  
  public List<Food> findAll();
  
  public Food find(Long id);
  
  public List<Food> findByName(String name);
  
  public void add(Food food);
  
  public void update(Food food);
  

}
