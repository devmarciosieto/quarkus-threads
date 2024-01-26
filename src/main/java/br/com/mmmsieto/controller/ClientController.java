package br.com.mmmsieto.controller;

import br.com.mmmsieto.controller.dtos.ClientAddressEntity;
import br.com.mmmsieto.domain.service.ClientService;
import br.com.mmmsieto.domain.service.ViaCepRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {

    @Inject
    @RestClient
    ViaCepRestClient viaCepRestClient;

    @Inject
    ClientService clientService;

    @GET
    @Path("/{cep}")
    public Response producer(@PathParam("cep") String cep) {
        long startTime = System.currentTimeMillis();

        List<ClientAddressEntity> clientAddressEntity = clientService.listWithCompletableFuture();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        System.out.println("O tempo de execução foi com threads --> : " + duration + " milissegundos");

        return Response.ok(clientAddressEntity).build();

    }

    @GET
    public Response list() {
        long startTime = System.currentTimeMillis();

        List<ClientAddressEntity> clientAddressEntity = clientService.listClient();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        System.out.println("O tempo de execução foi sem threads -->: " + duration + " milissegundos");

        return Response.ok(clientAddressEntity).build();

    }

}
