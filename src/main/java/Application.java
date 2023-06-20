import br.com.acme.model.ClienteModel;
import br.com.acme.model.PagamentoModel;
import br.com.acme.model.ProdutoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // 1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos.
        // Crie Pagamentos com:  a data de hoje, ontem e um do mês passado.
        ClienteModel breno = new ClienteModel("Breno");
        ClienteModel joao = new ClienteModel("Joao");
        ClienteModel maria = new ClienteModel("Maria");

        //TODO: Usar new BigDecimal em vez de valueOf
        ProdutoModel p1 = new ProdutoModel("Apologize", null, BigDecimal.valueOf(98.90));
        ProdutoModel p2 = new ProdutoModel("Te levar daqui", null, BigDecimal.valueOf(50.00));
        ProdutoModel p3 = new ProdutoModel("Ana Julia", null, BigDecimal.valueOf(25.30));

        PagamentoModel pag1 = new PagamentoModel(List.of(p1, p2), LocalDate.now(), breno);
        PagamentoModel pag2 = new PagamentoModel(List.of(p1, p2, p3), LocalDate.now().minusDays(1), joao);
        PagamentoModel pag3 = new PagamentoModel(List.of(p1), LocalDate.now().minusMonths(1), maria);

        // 2 - Ordene e imprima os pagamentos pela data de compra.

        List<PagamentoModel> pagamentos = new ArrayList<>(List.of(pag1, pag2, pag3));

        pagamentos.sort(Comparator.comparing(PagamentoModel::getDataCompra));
        System.out.println("Pagamentos ordenados pela data de compra:");
        pagamentos.forEach(pag -> System.out.println(pag.getCliente().getNome() + " comprou em: " + pag.getDataCompra()));

        System.out.println("--------------------------------------------------------");

        // TODO: 3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.

        // 4 -  Calcule o Valor de todos os pagamentos da Lista de pagamentos.
        double somaPagamentos = pagamentos.stream().mapToDouble(prod ->
                prod.getProdutos().stream().mapToDouble(preco ->
                        preco.getPreco().doubleValue()).sum()).sum();

        System.out.println("A soma de todos os pagamentos é: R$" + somaPagamentos);

        System.out.println("--------------------------------------------------------");

        // TODO: 5 - Imprima a quantidade de cada Produto vendido.
        List<List<String>> produtos = pagamentos.stream().map(prod ->
                prod.getProdutos().stream().map(ProdutoModel::getNome).collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(produtos);

        System.out.println("--------------------------------------------------------");

        // 6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente.

        Map<String, List<String>> clientesMap = new HashMap<>();
        pagamentos.forEach(pag ->
                clientesMap.put(pag.getCliente().getNome(),pag.getProdutos().stream().map(ProdutoModel::getNome).collect(Collectors.toList())));
        System.out.println(clientesMap);
    }

    /*
    ChronoUnit.MONTHS.between(periodo2.getStart(), periodo2.getEnd().orElse(hoje)

    Usar o map para iterar e depois o forEach(System.out::println)

    groupingBy - cria um map e o valor dentro é a chave do mapa

    6 -
    Map<Cliente, List<List<Produto>> clienteParProdutos = pagamentos.stream().collect(Collector.groupingBy(Pagamento::getCliente,
    Collectors.mapping(Pagamento::getProdutos, Collectors.toList())
    ));

    Map<Cliente, List<Produto> collect = clienteParaProdutos.entrySet().stream()
    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream)
    .collect(Collectors.toList())));

    7-

    Function<Pagamento, BigDecimal> reducingFunction = p -> p.getProdutos().stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
    Map<Cliente, BigDecimal> topClientes = pagamentos.stream().collect(Collector.groupingBy(Pagamento::getCliente),
    Collectors.reducing(BigDecimal.ZERO, reducingFunction, BigDecimal::add));

    topClientes.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
     */



}
