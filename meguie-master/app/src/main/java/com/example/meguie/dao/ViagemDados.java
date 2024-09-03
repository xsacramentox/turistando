package com.example.meguie.dao;

public class ViagemDados {

    private int Id;
    private String dataViagem;
    private String nomeCliente;
    private String nomeGuia;
    private String tituloRoteiro;
    private String StatusViagem;
    private String tipoPagamento;
    private int imagem;
    private String descricaoRoteiro;
    private String cidade;

    public String getDescricaoRoteiro() {
        return descricaoRoteiro;
    }

    public void setDescricaoRoteiro(String descricaoRoteiro) {
        this.descricaoRoteiro = descricaoRoteiro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public ViagemDados(String dataViagem, String nomeGuia, String tituloRoteiro, String statusViagem, String cidade, String descricao, int imagem) {
        this.dataViagem = dataViagem;
        this.nomeGuia = nomeGuia;
        this.tituloRoteiro = tituloRoteiro;
        this.StatusViagem = statusViagem;
        this.imagem = imagem;
        this.cidade = cidade;
        this.descricaoRoteiro = descricao;
    }

    public ViagemDados() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeGuia() {
        return nomeGuia;
    }

    public void setNomeGuia(String nomeGuia) {
        this.nomeGuia = nomeGuia;
    }

    public String getTituloRoteiro() {
        return tituloRoteiro;
    }

    public void setTituloRoteiro(String tituloRoteiro) {
        this.tituloRoteiro = tituloRoteiro;
    }

    public String getStatusViagem() {
        return StatusViagem;
    }

    public void setStatusViagem(String statusViagem) {
        StatusViagem = statusViagem;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
