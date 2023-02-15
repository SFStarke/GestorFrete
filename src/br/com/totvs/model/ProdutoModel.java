package br.com.totvs.model;

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
        return "=> Lote nº ["+this.getId()+"]. "+this.getItem()+". ("+this.getCaixa()+") Caixa(s) de "+this.getVolume()+" m³ por caixa ||";
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

}
