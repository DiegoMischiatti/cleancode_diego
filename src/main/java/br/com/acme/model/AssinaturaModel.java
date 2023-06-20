package br.com.acme.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AssinaturaModel {
    private BigDecimal mensalidade;
    private LocalDate inicio;
    private Optional<LocalDate> fim;
    private ClienteModel cliente;

    public AssinaturaModel(BigDecimal mensalidade, LocalDate inicio, LocalDate fim, ClienteModel cliente) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.fim = Optional.of(fim);
        this.cliente = cliente;
    }

    public AssinaturaModel(BigDecimal mensalidade, LocalDate inicio, ClienteModel cliente) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.cliente = cliente;
        this.fim = Optional.empty();
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public Optional<LocalDate> getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = Optional.of(fim);
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
