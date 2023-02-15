package br.com.totvs.model;

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
    public FreteModel(int id,String item, int caixa, double volume){
        this.setProdutos("=> Lote nº [" + id + "] " + item + ". (" + caixa + ") Caixa(s) de " + volume + "m³ por caixa ||");
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
}
