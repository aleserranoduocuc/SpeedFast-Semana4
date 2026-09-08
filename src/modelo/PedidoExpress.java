package modelo;

public class PedidoExpress extends Pedido {
    private int nivelUrgencia;
    private String tipoVehiculo;

    public PedidoExpress(int id, String direccion, double distancia, int nivelUrgencia, String tipoVehiculo) {
        super(id, direccion, distancia);
        this.nivelUrgencia = nivelUrgencia;
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = (int)(distancia * 2) - (nivelUrgencia * 2) + 5;
        return Math.max(tiempo, 5);
    }

    @Override
    public void asignarRepartidor() {
        String[] repartidores = {"Pedro Rojas", "Monica Torres", "Juan Silva"};
        this.repartidorAsignado = repartidores[(int)(Math.random() * repartidores.length)];
        historial.add("Repartidor express asignado: " + repartidorAsignado);
    }

    public int getNivelUrgencia() { return nivelUrgencia; }
    public String getTipoVehiculo() { return tipoVehiculo; }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Nivel de urgencia: " + nivelUrgencia + "/5");
        System.out.println("Tipo de vehiculo: " + tipoVehiculo);
    }
}