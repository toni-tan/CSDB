package com.ibm.training.bootcamp.cs.csdb.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.cs.csdb.domain.Food;
import com.ibm.training.bootcamp.cs.csdb.service.FoodService;
import com.ibm.training.bootcamp.cs.csdb.service.FoodServiceImpl;

@Path("/foodlist")
public class FoodController {
  
  private FoodService foodService;

  public FoodController() {
    this.foodService = new FoodServiceImpl();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Food> getFood(
      @QueryParam("name") String name) {

    try {
      List<Food> food;
      
      if (StringUtils.isAllBlank(name)) {
        food = foodService.findAll();
      } else {
        food = foodService.findByName(name);
      }
            
      return food;
      
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Food getFoodId(@PathParam("id") String id) {

    try {
      Long longId = Long.parseLong(id);
      Food food = foodService.find(longId);
      return food;
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addFood(Food food) {

    try {
     foodService.add(food);
      String result = "Food list saved : " + food.getName() + " " + food.getPrice() + "" + food.isInStock();
      return Response.status(201).entity(result).build();
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateFood(Food food) {

    try {
      foodService.upsert(food);
      String result = "Food list updated : " + food.getName() + " " + food.getPrice() + "" + food.isInStock();
      return Response.status(200).entity(result).build();
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

}
