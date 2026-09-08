package modelo;

public class PedidoComida extends Pedido {
    private String tipoComida;
    private int tiempoPreparacion;

    public PedidoComida(int id, String direccion, double distancia, String tipoComida, int tiempoPreparacion) {
        super(id, direccion, distancia);
        this.tipoComida = tipoComida;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int)(distancia * 3) + tiempoPreparacion;
    }

    @Override
    public void asignarRepartidor() {
        String[] repartidores = {"Luis Diaz", "Maria Gonzalez", "Carlos Perez"};
        this.repartidorAsignado = repartidores[(int)(Math.random() * repartidores.length)];
        historial.add("Repartidor de comida asignado: " + repartidorAsignado);
    }

    public String getTipoComida() { return tipoComida; }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Tipo de comida: " + tipoComida);
        System.out.println("Tiempo preparacion: " + tiempoPreparacion + " min");
    }
}