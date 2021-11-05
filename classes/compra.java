package trab.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class compra {
    private cliente cliente;
    private Calendar data;
    private double valor;

    public compra(cliente cliente, Calendar data, double valor) {
        this.cliente = cliente;
        this.data = data;
        this.valor = valor;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        if(cliente instanceof clienteEspecial){
            return this.cliente.getNome()+";"
            +this.cliente.getCpf()+";"
            +((clienteEspecial) cliente).getValeCompra()
            +";"+date.format(data.getTime())+";"
            +this.valor+"\n";
        }
        return this.cliente.getNome()+";"
        +this.cliente.getCpf()+";"
        +date.format(data.getTime())+";"
        +this.valor+"\n";
    }
}