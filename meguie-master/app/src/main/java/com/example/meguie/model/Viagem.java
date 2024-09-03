package com.example.meguie.model;

public class Viagem {

    private int Id;
    private String dataViagem;
    private int idCliente;
    private int idGuia;
    private int idRoteiro;
    private int idStatusViagem;
    private int idPagamento;

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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public int getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(int idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public int getIdStatusViagem() {
        return idStatusViagem;
    }

    public void setIdStatusViagem(int idStatusViagem) {
        this.idStatusViagem = idStatusViagem;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }
}
