package br.com.mmmsieto.domain.service;

import br.com.mmmsieto.controller.dtos.Clint;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Service {

    @Incoming("client-in")
    public void process(Clint clint) {
        // Processa a mensagem aqui
        System.out.println("Recebido Clint: " + clint);
    }

}
