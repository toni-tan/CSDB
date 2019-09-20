package com.ibm.training.bootcamp.cs.csdb.service;

import java.util.List;

import com.ibm.training.bootcamp.cs.csdb.domain.Food;

public interface FoodService {

  public List<Food> findAll();
  
  public Food find(Long id);
 
  public List<Food> findByName(String name);
  
  public void add(Food food);
  
  public void upsert(Food food);
  
 
}
