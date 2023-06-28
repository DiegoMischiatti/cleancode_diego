package br.com.acme.service;

import br.com.acme.enums.TipoAssinatura;
import br.com.acme.model.AssinaturaModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AssinaturaService {

    public void calcularMesesDeAssinatura(AssinaturaModel assinatura){

        LocalDate hoje = LocalDate.now();

        long tempoEmMeses = ChronoUnit.MONTHS.between(assinatura.getInicio(), assinatura.getFim().orElse(hoje));
        System.out.println("O total de meses de assinatura de " + assinatura.getCliente().getNome() + " é: " + tempoEmMeses + " meses.");
    }

    public void calcularPeriodoAssinaturas(List<AssinaturaModel> assinaturas){
        LocalDate hoje = LocalDate.now();

        assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            System.out.println("O total de meses de assinatura de " + ass.getCliente().getNome() + " é: " + meses + " meses.");
        });
    }

    public void calcularValorPagoPorAssinatura(List<AssinaturaModel> assinaturas) {
        LocalDate hoje = LocalDate.now();
                assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            BigDecimal valorPago = ass.getMensalidade().multiply(BigDecimal.valueOf(meses));
            System.out.println("O valor pago por " + ass.getCliente().getNome() + " até agora é de R$ " + valorPago);
        });
    }

    public BigDecimal calcularTaxaAssinatura(AssinaturaModel assinatura){
        BigDecimal taxa = new BigDecimal(0);

        switch (assinatura.getTipoAssinatura()) {
            case ANUAL:
                System.out.println("Taxa: 0%");
            break;
            case TRIMESTRAL:
                System.out.println("Taxa: 3%");
                taxa = assinatura.getMensalidade().multiply(new BigDecimal(3)).divide(new BigDecimal(100));

            break;
            case SEMESTRAL:
                System.out.println("Taxa: 6%");
                taxa = assinatura.getMensalidade().multiply(new BigDecimal(5)).divide(new BigDecimal(100));

            break;
        }
        return taxa;
    }

}
