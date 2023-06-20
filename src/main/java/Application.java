import br.com.acme.model.ClienteModel;
import br.com.acme.model.PagamentoModel;
import br.com.acme.model.ProdutoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // 1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos. [OK]
        // Crie Pagamentos com:  a data de hoje, ontem e um do mês passado.
        ClienteModel breno = new ClienteModel("Breno");
        ClienteModel joao = new ClienteModel("Joao");
        ClienteModel maria = new ClienteModel("Maria");

        ProdutoModel p1 = new ProdutoModel("Apologize", null, new BigDecimal("98.90"));
        ProdutoModel p2 = new ProdutoModel("Te levar daqui", null, new BigDecimal("50.00"));
        ProdutoModel p3 = new ProdutoModel("Ana Julia", null, new BigDecimal("25.30"));

        PagamentoModel pag1 = new PagamentoModel(List.of(p1, p2), LocalDate.now(), breno);
        PagamentoModel pag2 = new PagamentoModel(List.of(p1, p2, p3), LocalDate.now().minusDays(1), joao);
        PagamentoModel pag3 = new PagamentoModel(List.of(p1), LocalDate.now().minusMonths(1), maria);

        // 2 - Ordene e imprima os pagamentos pela data de compra. [OK]

        List<PagamentoModel> pagamentos = new ArrayList<>(List.of(pag1, pag2, pag3));

        pagamentos.sort(Comparator.comparing(PagamentoModel::getDataCompra));
        System.out.println("Pagamentos ordenados pela data de compra:");
        pagamentos.forEach(pag -> System.out.println(pag.getCliente().getNome() + " comprou em: " + pag.getDataCompra()));

        System.out.println("--------------------------------------------------------");

        // TODO: 3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.

        // 4 -  Calcule o Valor de todos os pagamentos da Lista de pagamentos. [OK]

        double somaPagamentos = pagamentos.stream().mapToDouble(prod ->
                prod.getProdutos().stream().mapToDouble(preco ->
                        preco.getPreco().doubleValue()).sum()).sum();

        System.out.println("A soma de todos os pagamentos é: R$" + somaPagamentos);

        System.out.println("--------------------------------------------------------");

        // 5 - Imprima a quantidade de cada Produto vendido [OK]

        System.out.println("Produtos vendidos: Quantidade");
        pagamentos.stream()
                .flatMap(pag -> pag.getProdutos().stream())
                .collect(Collectors.groupingBy(ProdutoModel::getNome, Collectors.counting()))
                .forEach((nome, qtd) -> {
                    System.out.println(nome + ": " + qtd);
                });

        System.out.println("--------------------------------------------------------");

        // 6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente. [OK]

        Map<ClienteModel, List<List<ProdutoModel>>> clienteModelListMap = pagamentos.stream().collect(Collectors.groupingBy(PagamentoModel::getCliente,
                Collectors.mapping(PagamentoModel::getProdutos, Collectors.toList())));

        Map<ClienteModel, List<ProdutoModel>> clienteParaProduto = clienteModelListMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream)
                        .collect(Collectors.toList())));

        // 7 - Qual cliente gastou mais? [OK]

        Function<PagamentoModel, BigDecimal> reducingFunction = p -> p.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<ClienteModel, BigDecimal> topClientes = pagamentos.stream()
                .collect(Collectors.groupingBy(PagamentoModel::getCliente,
                        Collectors.reducing(BigDecimal.ZERO, reducingFunction, BigDecimal::add)));

        topClientes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(cliente ->
                        System.out.println(cliente.getKey().getNome() + " gastou: R$ " + cliente.getValue()));

        System.out.println("--------------------------------------------------------");
    }

    /*
    ChronoUnit.MONTHS.between(periodo2.getStart(), periodo2.getEnd().orElse(hoje)
    */
}
