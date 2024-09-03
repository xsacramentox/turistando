package com.example.meguie.model;

public class Pagamento {

    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    @Override
    public java.lang.String toString() {
        return descricao;
    }
}
