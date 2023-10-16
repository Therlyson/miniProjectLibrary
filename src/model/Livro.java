package model;

import java.util.ArrayList;

public class Livro extends Exemplar{
    public Livro(String titulo, ArrayList<String> autores, String resum, String editora, int anoPublicacao, int qtdPaginas, String genero) {
        super(titulo, autores, resum, editora, anoPublicacao, qtdPaginas, genero);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
