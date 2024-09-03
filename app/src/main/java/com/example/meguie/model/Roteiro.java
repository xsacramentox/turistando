package com.example.meguie.model;

public class Roteiro {

    private int Id;
    private String Titulo;
    private String Cidade;
    private String Preco;
    private String Duracao;
    private int Imagem;

    public Roteiro(){
    }

    public int getImagem() {
        return Imagem;
    }

    public void setImagem(int imagem) {
        Imagem = imagem;
    }

    public Roteiro(String titulo, String cidade, String preco, String duracao, String descricao, int imagem) {
        Titulo = titulo;
        Cidade = cidade;
        Preco = preco;
        Duracao = duracao;
        Descricao = descricao;
        Imagem = imagem;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String preco) {
        Preco = preco;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String duracao) {
        Duracao = duracao;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    private String Descricao;

}
