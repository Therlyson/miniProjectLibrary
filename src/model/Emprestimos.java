package model;

import java.time.LocalDate;

public class Emprestimos extends Movimentacao{
    public Emprestimos(Exemplar exemplar, Usuario usuario, LocalDate data) {
        super(exemplar, usuario, data);
    }
}
