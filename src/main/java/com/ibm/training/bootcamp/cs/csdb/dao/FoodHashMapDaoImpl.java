package com.ibm.training.bootcamp.cs.csdb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.cs.csdb.domain.Food;

public class FoodHashMapDaoImpl implements FoodDao {
  
  static private FoodHashMapDaoImpl INSTANCE;
  static private final Map<Long, Food> FOOD_STORE;
  static private long id = 0;

  static {
    FOOD_STORE = new HashMap<>();
//    Song s1 = new Song(id++, "Flash", "X1", 2019, "K-Pop");
//    Song s2 = new Song(id++, "Paper Rings", "Taylor Swift", 2019, "Pop" );
//    Song s3 = new Song(id++, "Wait", "NF", 2015, "Rap");
//    FOOD_STORE.put(s1.getId(), s1);
//    FOOD_STORE.put(s2.getId(), s2);
//    FOOD_STORE.put(s3.getId(), s3);
  }

  private FoodHashMapDaoImpl() {
    
  }
  
  static public FoodHashMapDaoImpl getInstance( ) {
    
    FoodHashMapDaoImpl instance;
    if (INSTANCE != null) {
      instance = INSTANCE;
    } else {
      instance = new FoodHashMapDaoImpl();
      INSTANCE = instance;
    }
    
    return instance;
  }
  
  @Override
  public List<Food> findAll() {
    return new ArrayList<Food>(FOOD_STORE.values());
  }

  @Override
  public Food find(Long id) {
    return FOOD_STORE.get(id);
  }

  @Override
  public List<Food> findByName(String name) {
    List<Food> Food = FOOD_STORE.values().stream()
        .filter(food -> StringUtils.isBlank(name) || food.getName().equalsIgnoreCase(name))
        .collect(Collectors.toList());
    
//    List<Song> users = new ArrayList<>(FOOD_STORE.values());
//
//    List<Song> results = new ArrayList<>();
//    for (Song user : users) {
//      if ( (StringUtils.isBlank(firstName) || user.getFirstName().equalsIgnoreCase(firstName)) 
//          && (StringUtils.isBlank(lastName) || user.getLastName().equalsIgnoreCase(lastName))) {
//        results.add(user);
//      }
//    }
    
    //return results;
    return Food;
  }
  

  
  public void add(Food food) {
    if (food != null && food.getId() == null) {
      food.setId(id++);
      FOOD_STORE.put(food.getId(), food);
    }
//    return false;
  }

  @Override
  public void update(Food food) {
    if (food != null && food.getId() != null) {
      FOOD_STORE.put(food.getId(), food);
    }
  }



}
