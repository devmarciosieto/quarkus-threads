package br.com.mmmsieto.controller.dtos;

import java.util.UUID;

public record Address(
        UUID id,
        String zipCode,
        String address,
        String number,
        String neighborhood,
        String complement,
        String city
        ) {

        public Address {
                id = UUID.randomUUID();
        }
}
