package model;

import java.time.LocalDate;

public class Devolucao extends Movimentacao{
    private String yesOrNot;

    public Devolucao(Exemplar exemplar, Usuario usuario, LocalDate data, String yesOrNot) {
        super(exemplar, usuario, data);
        this.yesOrNot = yesOrNot;
    }

    public String getYesOrNot() {
        return yesOrNot;
    }
}
