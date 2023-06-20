package br.com.acme.model;

public class ClienteModel {
    private String nome;

    public ClienteModel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
