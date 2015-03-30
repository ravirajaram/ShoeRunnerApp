/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.controller;

import com.nord.shoerunner.capability.ShoeRunnerRequestCapability;
import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xqn7
 */
@Path("/shoerunner")
public class ShoeRunnerController {

    @GET
    @Path("returnall/employee/{employeeId}/upc/{upc}")
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<ShoeRunnerRequest> createAndReturn(@PathParam("upc") String upc, @PathParam("employeeId") String employeeId) {
        System.out.println("upc " + upc);
        return new ShoeRunnerRequestCapability().createRequestAndReturnAll(upc, employeeId);
    }

    @GET
    @Path("employee/{employeeId}/upc/{upc}")
    @Produces({MediaType.APPLICATION_JSON})
    public ShoeRunnerRequest create(@PathParam("upc") String upc, @PathParam("employeeId") String employeeId) {
        System.out.println("upc " + upc);
        return new ShoeRunnerRequestCapability().createRequest(upc, employeeId);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShoeRunnerRequest findById(@PathParam("id") String id) {
        System.out.println("id: " + id);
        return new ShoeRunnerRequestCapability().getRequestById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ShoeRunnerRequest> getAllRequests() {
        System.out.println("getAllRequests");
        return new ShoeRunnerRequestCapability().getAllRequests();
    }

}
