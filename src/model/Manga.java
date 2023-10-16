package model;

import java.util.ArrayList;

public class Manga extends Exemplar{
    private String tipo;

    public Manga(String titulo, ArrayList<String> autores, String resum, String editora, int anoPublicacao, int qtdPaginas, String genero, String tipo) {
        super(titulo, autores, resum, editora, anoPublicacao, qtdPaginas, genero);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Tipo: " + tipo + "\n";
    }
}
