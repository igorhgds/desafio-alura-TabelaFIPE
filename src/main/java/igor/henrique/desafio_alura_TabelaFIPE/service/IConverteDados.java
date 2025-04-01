package igor.henrique.desafio_alura_TabelaFIPE.service;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
