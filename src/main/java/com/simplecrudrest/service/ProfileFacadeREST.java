/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplecrudrest.service;

import com.simplecrudrest.entity.Profile;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author saddamtbg@gmail.com
 */
@Stateless
@Path("profile")
public class ProfileFacadeREST extends AbstractFacade<Profile> {
    @PersistenceContext(unitName = "com.simpleCrudRest_PU")
    private EntityManager em;
    
    private final Map<Integer, Profile> profiledb = new ConcurrentHashMap<Integer, Profile>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public ProfileFacadeREST() {
        super(Profile.class);
    }

    
    @POST
    @Produces("text/html")
    @Path("add")
    public Response createProfile(
            @FormParam("username") String username, @FormParam("phone") String phone,
            @FormParam("address") String address, @FormParam("city") String city,
            @FormParam("country") String country) {
        
        Profile pr = new Profile();
        pr.setId(idCounter.incrementAndGet());
        pr.setUsername(username);
        pr.setPhone(phone);
        pr.setAddress(address);
        pr.setCity(city);
        pr.setCountry(country);
        
        profiledb.put(pr.getId(), pr);
        System.out.println("Created profile " + pr.getId());
        
        String output = "Created profile <a href=\"profiles/" + pr.getId() + 
                "\">" + pr.getId() + "</a>";
        
        URI location = URI.create("/profiles/" + pr.getId());
        return Response.created(location).entity(output).build();   
    }
    
    @GET
    @Path("profiles/{id}")
    @Produces("text/plain")
    public Response getProfile(@PathParam("id") int id) {
        
        final Profile pr = profiledb.get(id);
        
        if(pr == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        
        String op = "Profile: " + pr.getUsername() + " from " + pr.getCity();
        
        return Response.ok(op).build();
    }
    

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Profile find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Profile> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Profile> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
