package br.com.totvs.model;

import java.util.Objects;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class FreteModel {

    private int id;
    private String produtos;

    public FreteModel() {
    }

    public FreteModel(String produtos) {
        this.produtos = produtos;
    }

    public FreteModel(int id, String produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public FreteModel(int id, String item, int caixa, double volume) {
        this.setProdutos("=> Lote nº [" + id + "] (" + caixa + ") Caixa(s) de " + item + ". " + volume + "m³ por caixa ||");
    }

    @Override
    public String toString() {
        return this.produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.produtos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FreteModel other = (FreteModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.produtos, other.produtos)) {
            return false;
        }
        return true;
    }

}
