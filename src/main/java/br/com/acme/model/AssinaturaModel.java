package br.com.acme.model;

import br.com.acme.enums.TipoAssinatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AssinaturaModel {
    private final BigDecimal mensalidade;
    private final LocalDate inicio;
    private final Optional<LocalDate> fim;
    private final ClienteModel cliente;
    private TipoAssinatura tipoAssinatura;
    private boolean isAtrasado;

    public AssinaturaModel(BigDecimal mensalidade, LocalDate inicio, LocalDate fim, ClienteModel cliente, TipoAssinatura tipoAssinatura) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.fim = Optional.of(fim);
        this.cliente = cliente;
        this.tipoAssinatura = tipoAssinatura;
        this.isAtrasado = false;
    }

    public AssinaturaModel(BigDecimal mensalidade, LocalDate inicio, ClienteModel cliente, TipoAssinatura tipoAssinatura) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.cliente = cliente;
        this.fim = Optional.empty();
        this.tipoAssinatura = tipoAssinatura;
        this.isAtrasado = false;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public LocalDate getInicio() {
        return inicio;
    }


    public Optional<LocalDate> getFim() {
        return fim;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public TipoAssinatura getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(TipoAssinatura tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public boolean isAtrasado() {
        return isAtrasado;
    }

    public void setAtrasado(boolean atrasado) {
        isAtrasado = atrasado;
    }
}
