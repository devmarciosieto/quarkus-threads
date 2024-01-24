package br.com.mmmsieto.domain.service;

import br.com.mmmsieto.controller.dtos.AddressEntity;
import br.com.mmmsieto.controller.dtos.ClientAddressEntity;
import br.com.mmmsieto.controller.dtos.ClientEntity;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientService {

    @Inject
    @RestClient
    ViaCepRestClient viaCepRestClient;

    @Inject
    ManagedExecutor managedExecutorService;

    public List<ClientEntity> list() {

        List<ClientEntity> clientEntities = new ArrayList<>();
        clientEntities.add(new ClientEntity(1L, "Client 1", "18125000"));
        clientEntities.add(new ClientEntity(2L, "Client 2", "15540000"));
        clientEntities.add(new ClientEntity(3L, "Client 3", "19160000"));
        clientEntities.add(new ClientEntity(7L, "Client 7", "08280540"));
        clientEntities.add(new ClientEntity(8L, "Client 8", "13232212"));
        clientEntities.add(new ClientEntity(9L, "Client 9", "18686170"));
        clientEntities.add(new ClientEntity(10L, "Client 10", "13504910"));
        clientEntities.add(new ClientEntity(11L, "Client 11", "13088170"));
        clientEntities.add(new ClientEntity(12L, "Client 12", "12224842"));
        clientEntities.add(new ClientEntity(13L, "Client 13", "13312127"));
        clientEntities.add(new ClientEntity(14L, "Client 14", "13905513"));
        clientEntities.add(new ClientEntity(15L, "Client 15", "13469491"));
        clientEntities.add(new ClientEntity(16L, "Client 16", "06402020"));
        clientEntities.add(new ClientEntity(17L, "Client 17", "11370010"));
        clientEntities.add(new ClientEntity(18L, "Client 18", "18119139"));
        clientEntities.add(new ClientEntity(19L, "Client 19", "08795250"));
        clientEntities.add(new ClientEntity(20L, "Client 20", "12420240"));
        clientEntities.add(new ClientEntity(21L, "Client 21", "12316470"));
        clientEntities.add(new ClientEntity(22L, "Client 22", "02205080"));
        clientEntities.add(new ClientEntity(23L, "Client 23", "02977110"));
        clientEntities.add(new ClientEntity(24L, "Client 24", "13253504"));
        clientEntities.add(new ClientEntity(25L, "Client 25", "06182170"));
        clientEntities.add(new ClientEntity(26L, "Client 26", "05882010"));
        clientEntities.add(new ClientEntity(27L, "Client 27", "17031730"));
        clientEntities.add(new ClientEntity(28L, "Client 28", "09607140"));
        clientEntities.add(new ClientEntity(29L, "Client 29", "12225020"));
        clientEntities.add(new ClientEntity(30L, "Client 30", "12225020"));

        return clientEntities;
    }

    public List<ClientAddressEntity> listClient() {

        List<ClientEntity> clientEntities = list();

        List<ClientAddressEntity> clientAddressEntities = new ArrayList<>();

        for (ClientEntity clientEntity : clientEntities) {
            ClientAddressEntity clientAddressEntity = getClientAddress(clientEntity);
            clientAddressEntities.add(clientAddressEntity);
        }

        return clientAddressEntities;
    }
    public List<ClientAddressEntity> listWithCompletableFuture() {

        List<ClientEntity> clientEntities = list();

        List<CompletableFuture<ClientAddressEntity>> completableFutures = new ArrayList<>();

        for (ClientEntity clientEntity : clientEntities) {
            CompletableFuture<ClientAddressEntity> completableFuture =
                    CompletableFuture.supplyAsync(() -> getCompletableFuture(clientEntity)
                            .join(), managedExecutorService);

            completableFutures.add(completableFuture);
        }

        try {
            return completableFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
        } catch (CompletionException e) {
            throw new RuntimeException(e);
        }
    }

    private CompletableFuture<ClientAddressEntity> getCompletableFuture(ClientEntity clientEntity) {
        ClientAddressEntity clientAddressEntity = getClientAddress(clientEntity);
        return CompletableFuture.completedFuture(clientAddressEntity);
    }


    public ClientAddressEntity getClientAddress(ClientEntity clientEntity) {

        AddressEntity addressEntity = viaCepRestClient.getCep(clientEntity.getCep());

        ClientAddressEntity clientAddressEntity = new ClientAddressEntity();
        clientAddressEntity.setId(clientEntity.getId());
        clientAddressEntity.setName(clientEntity.getName());
        clientAddressEntity.setCep(addressEntity.getCep());
        clientAddressEntity.setLogradouro(addressEntity.getLogradouro());
        clientAddressEntity.setComplemento(addressEntity.getComplemento());
        clientAddressEntity.setBairro(addressEntity.getBairro());
        clientAddressEntity.setLocalidade(addressEntity.getLocalidade());
        clientAddressEntity.setUf(addressEntity.getUf());
        clientAddressEntity.setIbge(addressEntity.getIbge());
        clientAddressEntity.setGia(addressEntity.getGia());
        clientAddressEntity.setDdd(addressEntity.getDdd());
        clientAddressEntity.setSiafi(addressEntity.getSiafi());

        return clientAddressEntity;
    }

}


