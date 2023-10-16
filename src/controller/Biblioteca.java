package controller;

import model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Exemplar> acervo;
    private ArrayList<Exemplar> acervo2;
    private  ArrayList<Usuario> usuarios;
    private ArrayList<Movimentacao> movimentacaos;

    public Biblioteca() {
        acervo = new ArrayList<>();
        usuarios = new ArrayList<>();
        movimentacaos = new ArrayList<>();
        acervo2 = new ArrayList<>();
    }

    public void cadastrarExemplar(Exemplar ex){
        acervo.add(ex);
        acervo2.add(ex);
    }

    public Exemplar consultarExemplar(String dado){
        for(Exemplar ex: acervo){
            if(ex.getTitulo().equalsIgnoreCase(dado)){
                return ex;
            }
            else if(ex.getResum().equalsIgnoreCase(dado)){
                return ex;
            }
            else{
                for(String autores: ex.getAutores()){
                    if(autores.equalsIgnoreCase(dado)){
                        return ex;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Exemplar> consultarTodos(String dado) {
        ArrayList<Exemplar> resultado = new ArrayList<>();
        for(Exemplar ex: acervo){
            if(ex.getTitulo().equalsIgnoreCase(dado)){
                resultado.add(ex);
            }
            else if(ex.getResum().equalsIgnoreCase(dado)){
                resultado.add(ex);
            }
            else{
                for(String autores: ex.getAutores()){
                    if(autores.equalsIgnoreCase(dado)){
                        resultado.add(ex);
                    }
                }
            }
        }
        return resultado;
    }

    public void cadastrarUsuario(Usuario pessoa){
        usuarios.add(pessoa);
    }

    public boolean verificarUsuario(String cpf){
        for(Usuario usuario: usuarios){
            if(usuario.getCpf().equalsIgnoreCase(cpf)){
                return true;
            }
        }
        return false;
    }

    public Usuario editarUsuario(String cpf, String novoNome, String novaData, String novoIndicador){
        for(Usuario usuario: usuarios){
            if(usuario.getCpf().equalsIgnoreCase(cpf)){
                usuario.setNome(novoNome);
                usuario.setDataNascimento(novaData);
                usuario.setIndicador(novoIndicador);
                return usuario;
            }
        }
        return null;
    }

    public boolean registrarEmprestimo(String titulo, String cpf, LocalDate data){
        Exemplar exemplar = null;
        for(Exemplar ex: acervo2){
            if(ex.getTitulo().equalsIgnoreCase(titulo)){
                exemplar = ex;
                acervo2.remove(ex); //esse for é necessário para se tiver dois exemplares com mesmo titulo
                break;              //não dá erro na hora de comparar se o exmplar já foi emprestado.
            }
        }
        if(exemplar == null){
            return false; //exemplar não existe
        }

        for(Movimentacao m: movimentacaos){
            if(m instanceof Emprestimos){
                if(m.getExemplar().equals(exemplar)){
                    return false; //exemplar já está emprestado para alguém
                }
            }
        }

        for(Usuario usuario1: usuarios){
            if(cpf.equalsIgnoreCase(usuario1.getCpf())){
                boolean key = verificarEmprestimo(usuario1);

                if(key){
                    return false; //usuario já pegou um livro emprestado e não pode mais
                }
                else{
                    Movimentacao m = new Emprestimos(exemplar, usuario1, data);
                    movimentacaos.add(m);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarEmprestimo(Usuario usuario){
        for(Movimentacao movimentacao: movimentacaos){
            if(movimentacao instanceof Emprestimos){
                if(movimentacao.getUsuario().equals(usuario)){
                    return true;
                }
            }
        }
        return false;
    }

    public double realizarDevolucao(String cpf, LocalDate data, String leitura){
        for(Movimentacao movimentacao: movimentacaos){
            if(movimentacao instanceof Emprestimos){
                if(movimentacao.getUsuario().getCpf().equals(cpf)){
                    movimentacaos.remove(movimentacao);

                    Movimentacao d = new Devolucao(movimentacao.getExemplar(), movimentacao.getUsuario(), data, leitura);
                    acervo2.add(movimentacao.getExemplar()); //adiciona o exemplar novamente no 2 acervo
                    movimentacaos.add(d);
                    if(leitura.equalsIgnoreCase("sim")){
                        movimentacao.getUsuario().contObras();
                    }

                    int atraso = verificarAtraso(data, movimentacao.getData());
                    if(atraso>7){
                        double multa = (atraso - 7) * 2.0; //2 R$ POR DIA DE ATRASO
                        return multa;
                    }
                    else if(atraso >= 0 && atraso<=7){ //testandp se a data não é inválida
                        return 0.0;
                    }
                    else{
                        return -1;
                    }
                }
            }
        }
        return -1;
    }

    public int verificarAtraso(LocalDate data1, LocalDate data2){
        Period diferenca = Period.between(data2, data1);

        int anos = diferenca.getYears();
        int meses = diferenca.getMonths();
        int dias = diferenca.getDays();

        int mesesConverte = meses * 30;
        int anoConverte = anos * 360;

        return dias+mesesConverte+anoConverte; //retornando o total de dias até a devolução
    }

    public ArrayList<Exemplar> relatorioLeituraUser(String cpf){
        ArrayList<Exemplar> resultado = new ArrayList<>();
        for(Movimentacao m: movimentacaos) {
            if(m instanceof Devolucao){
                Devolucao devolucao = (Devolucao) m; // Faz o cast para Devolucao
                if(devolucao.getUsuario().getCpf().equalsIgnoreCase(cpf)){
                    if(devolucao.getYesOrNot().equalsIgnoreCase("sim")){
                        resultado.add(devolucao.getExemplar());
                    }
                }
            }
        }
        return resultado;
    }

    public ArrayList<Movimentacao> relatorioGeral(){
        ArrayList<Movimentacao> resultado = new ArrayList<>();
        for(Movimentacao movimentacao: movimentacaos){
            resultado.add(movimentacao);
        }
        return resultado;
    }

    public ArrayList<Usuario> relatorioRanking(){
        ArrayList<Usuario> ranking = new ArrayList<>();
        Usuario usuarioMais = null;
        int i = 0;
        int ultimoMaior = 0, cont =0;

        for(Usuario user: usuarios){
            if(user.getIndicador().equalsIgnoreCase("criança") && user.getObrasLidas() > 0){
                cont ++;//apenas as crianças que fizeram a leitura
            }
        }

        while(i<cont){
            int maior = 0;

            for(Usuario us: usuarios){
                boolean flag = false;
                if(us.getIndicador().equalsIgnoreCase("criança")){
                    if(ultimoMaior == 0){  //testando a primeira vez
                        if(us.getObrasLidas() > maior){
                            maior = us.getObrasLidas();
                            usuarioMais = us; //adicionando o usuario com mais livros lidos a "usuarioMais"
                        }
                    }
                    else{ //testando as outras vezes
                        if(us.getObrasLidas() > maior && us.getObrasLidas() <= ultimoMaior){
                            for(Usuario novoUs: ranking){
                                if(us.equals(novoUs)){ //percorrendo pra saber se o usuario já está no ranking, evita repetiçoes
                                    flag = true;
                                    break;
                                }
                            }
                            if(!flag){  //se for falso(usuário ainda não está no ranking) adiciona ele ao ranking.
                                        // essa parte é necessária pois se tiver dois usuários com mesmo número de obras lidas
                                        // vai evitar adicionar o mesmo usuário e adicionar o novo.
                                maior = us.getObrasLidas();
                                usuarioMais = us;
                            }
                        }
                    }
                }
            }
            ultimoMaior = maior;  //responsável por pegar a maior quantidade do usuário com mais livros lidos até o momento
            ranking.add(usuarioMais);
            i++;
        }
        return ranking;
    }


//    public ArrayList<Usuario> relatorioRanking(){  versão do gpt
//        ArrayList<Usuario> ranking = new ArrayList<>();
//
//        for(Usuario us: usuarios){
//            if(us.getIndicador().equalsIgnoreCase("criança")){
//                ranking.add(us);
//            }
//        }
//
//        // Ordena a lista de usuários pelo número de obras lidas em ordem decrescente
//        Collections.sort(ranking, Comparator.comparingInt(Usuario::getObrasLidas).reversed());
//
//        return ranking;
//    }
}
