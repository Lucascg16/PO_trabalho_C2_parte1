package trab.classes;

import java.util.ArrayList;
import java.util.Calendar;

import trab.Interface.*;

public class cadCompra implements Vetor, Ordernacao {
    private ArrayList<compra> vetCompra;

    public cadCompra() {
        this.vetCompra = new ArrayList<>();
    }

    public ArrayList<compra> getVetCompra() {
        return vetCompra;
    }

    public void setVetCompra(ArrayList<compra> vetCompra) {
        this.vetCompra = vetCompra;
    }

    @Override
    public compra get(int pos) {
        return vetCompra.get(pos);
    }

    @Override
    public void insere(compra compra) {
        vetCompra.add(compra);
    }

    @Override
    public void remove(String cpf, Calendar data) {
        for (int i = 0; i < vetCompra.size(); i++) {
            if (vetCompra.get(i).getCliente().getCpf().equals(cpf) && vetCompra.get(i).getData() == data) {
                vetCompra.remove(i);
                i--;
            }
        }
    }

    // Ordenacoes
    @Override
    public void insercaoDireta() {
        int i, j;
        compra temp;

        for (i = 1; i < this.vetCompra.size(); i++) {
            temp = this.vetCompra.get(i);
            j = i - 1;
            while ((j >= 0) && (verificarMaior(j, temp) == 1)) {
                this.vetCompra.set(j + 1, this.vetCompra.get(j));
                j--;
            }
            this.vetCompra.set(j + 1, temp);
        }
    }

    @Override
    public void shellSort() {
        int i, j, h;
        compra temp;

        h = 1;
        do {
            h = 3 * h + 1;
        } while (h < this.vetCompra.size());
        do {
            h = h / 3;
            for (i = h; i < this.vetCompra.size(); i++) {
                temp = this.vetCompra.get(i);
                j = i;
                while (verificarMaior((j - 1), temp) == 1) {
                    this.vetCompra.set(j, this.vetCompra.get(j - h));
                    j -= h;
                    if (j < h) {
                        break;
                    }
                }
                this.vetCompra.set(j, temp);
            }
        } while (h != 1);
    }

    @Override
    public void quickSort() {
        quick(0, this.vetCompra.size() - 1);
    }

    private void quick(int esq, int dir) {
        int i = esq, j = dir;
        compra temp, pivo;

        pivo = this.vetCompra.get((i + j) / 2);
        do {
            while (verificarMaior(i, pivo) == -1) {
                i++;
            }
            while (verificarMaior(j, pivo) == 1) {
                j--;
            }
            if (i <= j) {
                temp = this.vetCompra.get(i);
                this.vetCompra.set(i, this.vetCompra.get(j));
                this.vetCompra.set(j, temp);
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j) {
            quick(esq, j);
        }
        if (dir > i) {
            quick(i, dir);
        }
    }

    @Override
    public void quickComInsercao() {
        quickWithInsertion(0, this.vetCompra.size() - 1);
    }

    private void quickWithInsertion(int esq, int dir) {
        int i = esq, j = dir;
        compra temp, pivo;

        pivo = this.vetCompra.get((i + j) / 2);
        do {
            while (verificarMaior(i, pivo) == -1) {
                i++;
            }
            while (verificarMaior(j, pivo) == 1) {
                j--;
            }
            if (i <= j) {
                temp = this.vetCompra.get(i);
                this.vetCompra.set(i, this.vetCompra.get(j));
                this.vetCompra.set(j, temp);
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j) {
            if ((j - esq) <= 20) {
                modInsertionSort(esq, j);
            } else {
                quickWithInsertion(esq, j);
            }
        }
        if (dir > i) {
            if ((dir - i) <= 20) {
                modInsertionSort(i, dir);
            } else {
                quickWithInsertion(i, dir);
            }
        }
    }

    private void modInsertionSort(int ini, int fim) {
        int j;
        compra temp;

        for (int i = ini; i <= fim; i++) {
            temp = this.vetCompra.get(i);
            j = i - 1;
            while ((j >= 0) && (verificarMaior(j, temp) == 1)) {
                this.vetCompra.set(j + 1, this.vetCompra.get(j));
                j--;
            }
            this.vetCompra.set(j + 1, temp);
        }
    }

    public int verificarMaior(int j, compra temp) {
        if (this.vetCompra.get(j).getCliente().getCpf().compareTo(temp.getCliente().getCpf()) > 0) {
            return 1;
        } else if (this.vetCompra.get(j).getCliente().getCpf().compareTo(temp.getCliente().getCpf()) < 0) {
            return -1;
        } else {
            if (this.vetCompra.get(j).getData().compareTo(temp.getData()) > 0) {
                return 1;
            } else if (this.vetCompra.get(j).getData().compareTo(temp.getData()) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
