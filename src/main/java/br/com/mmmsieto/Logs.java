package br.com.mmmsieto;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Logs {

    @Scheduled(every="2s")
    void executarCadaDoisSegundos() {
        imprimirUsoMemoria();
    }

    static void imprimirUsoMemoria() {
        System.out.println("Uso de memória: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
        System.out.println("Uso de memória: " + emMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Máximo de memória: " + emMegabytes(Runtime.getRuntime().maxMemory()));
        System.out.println("Memória livre: " + emMegabytes(Runtime.getRuntime().freeMemory()));
        System.out.println("Memória total: " + emMegabytes(Runtime.getRuntime().totalMemory()));
        System.out.println("Memória usada: " + emMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("-------------------------------------------------");
    }

    static String emMegabytes(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }

}
