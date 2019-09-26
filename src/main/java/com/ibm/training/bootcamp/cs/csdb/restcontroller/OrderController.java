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

import com.ibm.training.bootcamp.cs.csdb.domain.Order;
import com.ibm.training.bootcamp.cs.csdb.service.OrderService;
import com.ibm.training.bootcamp.cs.csdb.service.OrderServiceImpl;

@Path("/orderlist")
public class OrderController {

  private OrderService orderService;

  public OrderController() {
    this.orderService = new OrderServiceImpl();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Order> getOrder(
      @QueryParam("cName") String cName) {

    try {
      List<Order> order;
      
      if (StringUtils.isAllBlank(cName)) {
        order = orderService.findAll();
      } else {
        order = orderService.findByName(cName);
      }
      return order;
      
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

  @GET
  @Path("{order_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Order getFoodId(@PathParam("order_id") String order_id) {

    try {
      Long longId = Long.parseLong(order_id);
      Order order = orderService.find(longId);
      return order;
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addOrder(Order order) {

    try {
     orderService.add(order);
      String result = "Order list saved : " + order.getcName() + " " + order.getAddress() + " "
          + order.getContact() + " " + order.getTotal() + " "  
              + order.getCalculatedTotal() + " " + order.getStatus() + " " 
                  + order.getQuantity() + " "+ order.getId() ;
      return Response.status(201).entity(result).build();
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

  @PUT
  @Path("/update/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateOrder(Order order) {

    try {
      orderService.upsert(order);
      String result = "Order list updated : " + order.getcName() + " " + order.getAddress() + " "
          + order.getContact() + " " + order.getTotal() + " "  
          + order.getCalculatedTotal() + " " + order.getStatus() + " " 
              + order.getQuantity() + " "+ order.getId() ;
      return Response.status(200).entity(result).build();
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }

  }

}

