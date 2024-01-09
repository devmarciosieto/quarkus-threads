package br.com.mmmsieto.controller.dtos;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ClintDeserializer extends ObjectMapperDeserializer<Clint> {
    public ClintDeserializer() {
        super(Clint.class);
    }
}