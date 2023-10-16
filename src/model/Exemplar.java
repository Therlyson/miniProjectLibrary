package model;

import java.util.ArrayList;

public abstract class Exemplar {
    protected String titulo;
    protected ArrayList<String> autores;
    protected String resum;
    protected String editora;
    protected int anoPublicacao;
    protected int qtdPaginas;
    protected String genero;

    public Exemplar(String titulo, ArrayList<String> autores, String resum, String editora, int anoPublicacao, int qtdPaginas, String genero) {
        this.titulo = titulo;
        this.autores = autores;
        this.resum = resum;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.qtdPaginas = qtdPaginas;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public String getResum() {
        return resum;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "\n" +
                "Autores: " + autores + "\n" +
                "Resumo: " + resum + "\n" +
                "Editora: " + editora + "\n" +
                "Ano de publicação: " + anoPublicacao + "\n" +
                "Quantidade de páginas: " + qtdPaginas + "\n" +
                "Genêro: " + genero + "\n";
    }
}
