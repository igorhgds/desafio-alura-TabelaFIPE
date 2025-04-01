package igor.henrique.desafio_alura_TabelaFIPE.principal;

import igor.henrique.desafio_alura_TabelaFIPE.model.Dados;
import igor.henrique.desafio_alura_TabelaFIPE.service.ConsumoAPI;
import igor.henrique.desafio_alura_TabelaFIPE.service.ConverteDados;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;


public class Principal {

    public void exibeMenu() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ConsumoAPI consumoAPI = new ConsumoAPI();
        ConverteDados converteDados = new ConverteDados();

        final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

        var menu = """
                *** OPÇÕES ***
                
                * Carro
                * Moto
                * Caminhão
                
                Digite uma das opções para consulta:
                """;

            //Escolha da opção de consulta
        System.out.println(menu);
        var opcao = scanner.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

            //Retornando o Json do endereco de acordo com a escolha
        var json = consumoAPI.obterDados(endereco);
        System.out.println(json);

            //Exibindo a Lista de marcas da opcao escolhida
        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

            //Recebendo o código da marca pretendida
        System.out.println("Informe o código da marca para consulta");
        var codigoMarca = scanner.nextLine();
    }
}
