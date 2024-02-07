package com.example.controller;

import java.util.HashSet;
import org.eclipse.microprofile.jwt.Claims;

import com.example.data.JsonRequest;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/token")
@RequestScoped
public class GenerateToken {
    /**
     * Generate JWT token
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String generateToken(JsonRequest request) {
      String token =
         Jwt.issuer("https://example.com/issuer") 
           .upn(request.getEmail()) 
           .groups(new HashSet<>(request.getRoles()))
           .claim(Claims.birthdate.name(), request.getBirthdate()) 
         .sign();
      return token;
    }
}