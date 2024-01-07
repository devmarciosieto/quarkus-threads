package br.com.mmmsieto.controller.dtos;

import java.util.UUID;

public record Clint(UUID id, String name, Address address) {
    public Clint {
        id = UUID.randomUUID();
    }
}
