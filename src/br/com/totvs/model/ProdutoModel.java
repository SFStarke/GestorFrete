package br.com.totvs.model;

import java.util.Objects;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class ProdutoModel {

    private int id;
    private String item;
    private int caixa;
    private double volume;
    private double volumeTotal;

    public ProdutoModel() {
    }

    public ProdutoModel(String item, int caixa, double volume) {
        super();
        this.item = item;
        this.caixa = caixa;
        this.volume = volume;
    }

    public ProdutoModel(int id, String item, int caixa, double volume, double volumeTotal) {
        super();
        this.id = id;
        this.item = item;
        this.caixa = caixa;
        this.volume = volume;
        this.volumeTotal = volumeTotal;
    }

    @Override
    public String toString() {
        return "=> Lote nº ["+id+"] ("+caixa+") Caixa(s) de "+item+". "+volume+"m³ por caixa ||";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.item);
        hash = 23 * hash + this.caixa;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.volume) ^ (Double.doubleToLongBits(this.volume) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.volumeTotal) ^ (Double.doubleToLongBits(this.volumeTotal) >>> 32));
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
        final ProdutoModel other = (ProdutoModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.caixa != other.caixa) {
            return false;
        }
        if (Double.doubleToLongBits(this.volume) != Double.doubleToLongBits(other.volume)) {
            return false;
        }
        if (Double.doubleToLongBits(this.volumeTotal) != Double.doubleToLongBits(other.volumeTotal)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }
    
}
