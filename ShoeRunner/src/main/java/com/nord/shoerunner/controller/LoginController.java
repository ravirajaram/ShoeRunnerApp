/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.controller;

import com.nord.shoerunner.capability.ShoeRunnerRequestCapability;
import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xqn7
 */
@Path("/login")
public class LoginController {

    @GET
    @Path("user/{user}/role/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShoeRunnerRequest> findByName(@PathParam("user") String user, @PathParam("role") String role) {
        System.out.println("user: " + user);
        System.out.println("role: " + role);
        return new ShoeRunnerRequestCapability().getShoeRunnerRequestList();
    }
  
    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShoeRunnerRequest findById(@PathParam("id") String id) {
        System.out.println("id: " + id);
        return new ShoeRunnerRequestCapability().getShoeRunnerRequestById(id);
    }

    @GET
    @Path("upc/{upc}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ShoeRunnerRequest> create(@PathParam("upc") String upc) {
        System.out.println("upc " + upc);
        return new ShoeRunnerRequestCapability().createRequest(upc);
    }

}
