import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;
import repartidor.Repartidor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  🚀 SPEEDFAST - SIMULACIÓN DE ENTREGAS CONCURRENTES");
        System.out.println("==================================================\n");

        System.out.println("📦 Creando pedidos...");

        List<PedidoComida> pedidosCamila = Arrays.asList(
                new PedidoComida(101, "Av. Libertador 1234", 5.5, "Italiana", 15),
                new PedidoComida(102, "Calle San Martín 456", 3.2, "Mexicana", 10),
                new PedidoComida(103, "Av. Providencia 789", 4.8, "China", 20)
        );

        List<PedidoExpress> pedidosLuis = Arrays.asList(
                new PedidoExpress(201, "Av. Santa Rosa 567", 7.0, 5, "Moto"),
                new PedidoExpress(202, "Calle Los Presidentes 890", 2.5, 4, "Moto"),
                new PedidoExpress(203, "Av. Vitacura 321", 6.0, 3, "Auto")
        );

        List<PedidoEncomienda> pedidosDaniela = Arrays.asList(
                new PedidoEncomienda(301, "Av. Las Condes 100", 8.5, 15.0, true),
                new PedidoEncomienda(302, "Calle Nueva 200", 4.0, 8.5, false),
                new PedidoEncomienda(303, "Av. Apoquindo 300", 10.0, 20.0, true)
        );

        System.out.println("👤 Creando repartidores...\n");
        Repartidor repartidor1 = new Repartidor("Camila", new ArrayList<>(pedidosCamila));
        Repartidor repartidor2 = new Repartidor("Luis", new ArrayList<>(pedidosLuis));
        Repartidor repartidor3 = new Repartidor("Daniela", new ArrayList<>(pedidosDaniela));

        System.out.println("⚡ Iniciando entregas concurrentes...\n");
        System.out.println("--- INICIO DE SIMULACIÓN ---\n");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(repartidor1);
        executor.submit(repartidor2);
        executor.submit(repartidor3);

        executor.shutdown();

        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("⏰ Tiempo de espera agotado. Forzando finalización...");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            System.out.println("❌ Error en la ejecución concurrente: " + e.getMessage());
        }

        System.out.println("\n--- FIN DE SIMULACIÓN ---");
        System.out.println("\n📊 ESTADO FINAL DE PEDIDOS:");

        for (PedidoComida p : pedidosCamila) {
            System.out.println("  PedidoComida #" + p.getId() + " - Estado: " + p.getEstado() +
                    " - Repartidor: " + p.getRepartidorAsignado());
        }
        for (PedidoExpress p : pedidosLuis) {
            System.out.println("  PedidoExpress #" + p.getId() + " - Estado: " + p.getEstado() +
                    " - Repartidor: " + p.getRepartidorAsignado());
        }
        for (PedidoEncomienda p : pedidosDaniela) {
            System.out.println("  PedidoEncomienda #" + p.getId() + " - Estado: " + p.getEstado() +
                    " - Repartidor: " + p.getRepartidorAsignado());
        }

        System.out.println("\n==================================================");
        System.out.println("  ✅ SIMULACIÓN COMPLETADA");
        System.out.println("==================================================");
    }
}