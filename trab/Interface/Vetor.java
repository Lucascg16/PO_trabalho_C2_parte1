package trab.Interface;

import java.util.Calendar;

import trab.classes.compra;

public interface Vetor {
    
    public compra get(int pos);
    public void insere(compra compra);
    public void remove(String cpf, Calendar data);
}