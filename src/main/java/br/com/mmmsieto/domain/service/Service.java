package br.com.mmmsieto.domain.service;

import br.com.mmmsieto.controller.dtos.Clint;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class Service {

    @Incoming("client-in")
    public Uni<Void> process(Clint clint) {
        return Uni.createFrom().item(clint)
                .onItem().call(this::processClint)
                .onItem().ignore().andContinueWithNull();
    }

    private Uni<Void> processClint(Clint clint) {
        return Uni.createFrom().voidItem()
                .onItem().delayIt().by(Duration.ofSeconds(3))
                .onTermination().invoke(() -> printClint(clint));
    }

    private void printClint(Clint clint) {
        System.out.println("///////////////////////////////////////");
        System.out.println("Recebido Clint: " + clint);
        LocalTime agora = LocalTime.now();
        System.out.println("-------------------------------------------------------");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = agora.format(formatador);
        System.out.println(horaFormatada);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int activeThreadCount = Thread.activeCount();
        System.out.println("Número aproximado de threads ativas: " + activeThreadCount);

        System.out.println("**************************************");
    }
}