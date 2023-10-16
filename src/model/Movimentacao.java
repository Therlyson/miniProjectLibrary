package model;

import java.time.LocalDate;
import java.util.Locale;

public abstract class Movimentacao {
    private Exemplar exemplar;
    private Usuario usuario;
    private LocalDate data;

    public Movimentacao(Exemplar exemplar, Usuario usuario, LocalDate data) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.data = data;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getData() {
        return data;
    }
}
