package modelo;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import java.util.ArrayList;
import java.util.List;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected int id;
    protected String direccion;
    protected double distancia;
    protected String repartidorAsignado;
    protected String estado;
    protected List<String> historial;

    public Pedido(int id, String direccion, double distancia) {
        this.id = id;
        this.direccion = direccion;
        this.distancia = distancia;
        this.estado = "Pendiente";
        this.historial = new ArrayList<>();
        this.historial.add("Pedido creado - Pendiente");
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public double getDistancia() { return distancia; }
    public String getRepartidorAsignado() { return repartidorAsignado; }
    public String getEstado() { return estado; }

    public void setRepartidorAsignado(String repartidor) {
        this.repartidorAsignado = repartidor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        historial.add("Estado cambiado a: " + estado);
    }

    public void mostrarResumen() {
        System.out.println("\n--- RESUMEN DEL PEDIDO ---");
        System.out.println("ID: #" + id);
        System.out.println("Direccion: " + direccion);
        System.out.println("Distancia: " + distancia + " km");
        System.out.println("Estado: " + estado);
        System.out.println("Repartidor: " + (repartidorAsignado != null ? repartidorAsignado : "No asignado"));
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " minutos");
    }

    public abstract int calcularTiempoEntrega();

    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor Generico";
        historial.add("Repartidor asignado: " + repartidorAsignado);
    }

    public void asignarRepartidor(String nombre) {
        this.repartidorAsignado = nombre;
        historial.add("Repartidor asignado manualmente: " + nombre);
    }

    @Override
    public void despachar() {
        if (estado.equals("Cancelado")) {
            System.out.println("No se puede despachar un pedido cancelado.");
            return;
        }
        if (repartidorAsignado == null) {
            System.out.println("No hay repartidor asignado. Asignando automaticamente...");
            asignarRepartidor();
        }
        setEstado("En camino");
        historial.add("Pedido despachado");
        System.out.println("Pedido despachado correctamente.");
    }

    @Override
    public void cancelar() {
        if (estado.equals("Entregado")) {
            System.out.println("No se puede cancelar un pedido ya entregado.");
            return;
        }
        setEstado("Cancelado");
        historial.add("Pedido cancelado");
        System.out.println("Pedido cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {
        System.out.println("\n--- HISTORIAL DEL PEDIDO #" + id + " ---");
        for (String evento : historial) {
            System.out.println("  - " + evento);
        }
    }
}