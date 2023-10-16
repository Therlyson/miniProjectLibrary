package model;

import java.util.ArrayList;

public class Revista extends Exemplar{
    private int volume;
    private String mesPubli;

    public Revista(String titulo, ArrayList<String> autores, String resum, String editora, int anoPublicacao, int qtdPaginas, String genero, int volume, String mesPubli) {
        super(titulo, autores, resum, editora, anoPublicacao, qtdPaginas, genero);
        this.volume = volume;
        this.mesPubli = mesPubli;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getMesPubli() {
        return mesPubli;
    }

    public void setMesPubli(String mesPubli) {
        this.mesPubli = mesPubli;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Volume: " + volume + "\n" +
                "Mês de publicação: " + mesPubli + "\n";
    }
}
