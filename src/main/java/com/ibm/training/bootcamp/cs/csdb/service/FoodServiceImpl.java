package com.ibm.training.bootcamp.cs.csdb.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.cs.csdb.dao.FoodDao;
import com.ibm.training.bootcamp.cs.csdb.dao.FoodJdbcDaoImpl;
import com.ibm.training.bootcamp.cs.csdb.domain.Food;

public class FoodServiceImpl implements FoodService {
  
  FoodDao foodDao;
  
  public FoodServiceImpl() {
    this.foodDao = FoodJdbcDaoImpl.getInstance();
    //this.userDao = UserHashMapDaoImpl.getInstance();
  }
  
  @Override
  public List<Food> findAll() {
    return foodDao.findAll();
  }

  @Override
  public Food find(Long id) {
    return foodDao.find(id);
  }

  @Override
  public List<Food> findByName(String name) {
    return foodDao.findByName(name);
  }

  @Override
  public String add(Food food) {
    String serviceMessage;
    
    if (validate(food)) {
      if(foodDao.add(food)) {
        serviceMessage = "Food name already exists";
      }
      else {
        serviceMessage = "Food name is saved.";
      }
    } else {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
    
    return serviceMessage;
  }

  @Override
  public void upsert(Food food) {
    if (validate(food)) {
      if(food.getId() != null && food.getId() >= 0) {
        foodDao.update(food);
      } else {
        foodDao.add(food);
      }
    } else {
      throw new IllegalArgumentException("Fields cannot be blank.");
    }
  }

  
  private boolean validate(Food food) {
    return !StringUtils.isAnyBlank(food.getName());
  }

}


