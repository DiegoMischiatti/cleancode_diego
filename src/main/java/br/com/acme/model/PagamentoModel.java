package br.com.acme.model;

import java.time.LocalDate;
import java.util.List;

public class PagamentoModel {

    private List<ProdutoModel> produtos;
    private LocalDate dataCompra;
    private ClienteModel cliente;

    public PagamentoModel(List<ProdutoModel> produtos, LocalDate dataCompra, ClienteModel cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
