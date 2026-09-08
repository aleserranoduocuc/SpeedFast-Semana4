package modelo;

public class PedidoEncomienda extends Pedido {
    private double peso;
    private boolean requiereFirma;

    public PedidoEncomienda(int id, String direccion, double distancia, double peso, boolean requiereFirma) {
        super(id, direccion, distancia);
        this.peso = peso;
        this.requiereFirma = requiereFirma;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int)(distancia * 2 + peso * 1.5);
    }

    @Override
    public void asignarRepartidor() {
        String[] repartidores = {"Daniela Tapia", "Roberto Soto", "Ana Martinez"};
        this.repartidorAsignado = repartidores[(int)(Math.random() * repartidores.length)];
        historial.add("Repartidor de encomienda asignado: " + repartidorAsignado);
    }

    public double getPeso() { return peso; }
    public boolean isRequiereFirma() { return requiereFirma; }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Requiere firma: " + (requiereFirma ? "Si" : "No"));
    }
}