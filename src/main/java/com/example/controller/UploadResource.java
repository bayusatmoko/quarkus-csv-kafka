package com.example.controller;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.example.data.FormData;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Path("/upload")
@RequestScoped
public class UploadResource {

    @Inject
    @Channel("uploadcsv-out")
    Emitter<String> uploadDataEmitter;

    @Inject
    JsonWebToken jwt;
    @Inject
    @Claim(standard = Claims.birthdate)
    String birthdate;

    @POST
    @Path("/csv")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({ "User", "Admin" })
    public String uploadFile(@Context SecurityContext ctx, @MultipartForm FormData formData) throws InternalServerErrorException {
        if (ctx.getUserPrincipal() == null) {
            return "Upload failed";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
        InputStream fileStream = formData.file;
        processAndSendToKafka(fileStream);
        return "Upload successful";
        }
    }

    private void processAndSendToKafka(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                sendToKafka(Arrays.asList(columns));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToKafka(List<String> data) {
        String combinedData = String.join(",", data);
        uploadDataEmitter.send(combinedData);
    }
}