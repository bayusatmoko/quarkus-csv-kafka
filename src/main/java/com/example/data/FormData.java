package com.example.data;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class FormData {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    @FormParam("file")
    @PartType(MediaType.TEXT_PLAIN)
    public String fileName;

    public FormData() {

    }
}
