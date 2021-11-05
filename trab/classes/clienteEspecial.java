package trab.classes;

public class clienteEspecial extends cliente{
    private double valeCompra;

    public clienteEspecial(String nome, String cpf, double valeCompra) {
        super(nome, cpf);
        this.valeCompra = valeCompra;
    }

    public double getValeCompra() {
        return valeCompra;
    }

    public void setValeCompra(double valeCompra) {
        this.valeCompra = valeCompra;
    }    
}
