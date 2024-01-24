package br.com.mmmsieto.domain.service;

import br.com.mmmsieto.controller.dtos.AddressEntity;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/ws")
@RegisterRestClient(configKey="viacep-api")
public interface ViaCepRestClient {

    @GET
    @Path("/{cep}/json/")
    AddressEntity getCep(@PathParam("cep") String cep);
}

