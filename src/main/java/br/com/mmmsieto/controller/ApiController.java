package br.com.mmmsieto.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/consumer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiController {



    @GET
    public Response producer() {
        return Response.ok("Consumer").build();
    }

}
