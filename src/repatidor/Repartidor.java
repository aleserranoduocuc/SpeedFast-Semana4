package repartidor;

import modelo.Pedido;
import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> pedidos;
    private Random random;

    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
        this.random = new Random();
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        System.out.println("[Repartidor: " + nombre + "] Comenzando su jornada...");
        System.out.println("[Repartidor: " + nombre + "] Tiene " + pedidos.size() + " pedidos para entregar.");

        for (Pedido pedido : pedidos) {
            try {
                // Asignar el repartidor al pedido
                pedido.asignarRepartidor(nombre);

                // Calcular tiempo de entrega
                int tiempoEstimado = pedido.calcularTiempoEntrega();
                int tiempoEntrega = (tiempoEstimado * 100) + random.nextInt(500);

                System.out.println("[Repartidor: " + nombre + "] Entregando " +
                        pedido.getClass().getSimpleName() + " #" + pedido.getId() +
                        " en " + pedido.getDireccion() + "...");

                // Simular la entrega
                Thread.sleep(tiempoEntrega);

                // Marcar como entregado (directamente, sin llamar a despachar)
                pedido.setEstado("Entregado");

                System.out.println("[Repartidor: " + nombre + "] ✅ Pedido #" +
                        pedido.getId() + " entregado. (Tiempo: " + tiempoEntrega + "ms)");

            } catch (InterruptedException e) {
                System.out.println("[Repartidor: " + nombre + "] ❌ Error al entregar pedido #" +
                        pedido.getId() + ": " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[Repartidor: " + nombre + "] ❌ Error inesperado: " + e.getMessage());
            }
        }

        System.out.println("[Repartidor: " + nombre + "] 🎉 Jornada completada! Entregó " +
                pedidos.size() + " pedidos.");
    }
}