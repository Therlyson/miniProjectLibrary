package model;

public class Usuario {
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String indicador;
    private int obrasLidas = 0;

    public Usuario(String nome, String dataNascimento, String cpf, String indicador) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.indicador = indicador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador() {
        return indicador;
    }

    public int getObrasLidas() {
        return obrasLidas;
    }

    public void setObrasLidas(int obrasLidas) {
        this.obrasLidas = obrasLidas;
    }

    public void contObras(){
        obrasLidas++;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Data de nascimento: " + dataNascimento + "\n" +
                "CPF: " + cpf + "\n" +
                "Indicador: " + indicador + "\n";
    }
}
