package view;

import controller.Biblioteca;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Bem-vindo ao sistema de biblioteca!");

        while (true) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (escolha) {
                case 1:
                    cadastrarItem(biblioteca, scanner);
                    break;
                case 2:
                    consultarAcervo(biblioteca, scanner);
                    break;
                case 3:
                    cadastrarUsuario(biblioteca, scanner);
                    break;
                case 4:
                    editarUsuario(biblioteca, scanner);
                    break;
                case 5:
                    emprestimoExemplar(biblioteca, scanner);
                    break;
                case 6:
                    devolucaoExemplar(biblioteca, scanner);
                    break;
                case 7:
                    emitirRelatorioLeitura(biblioteca, scanner);
                    break;
                case 8:
                    relatorioGeralDeEmpEDev(biblioteca);
                    break;
                case 9:
                    rankingMaisLidosCrianca(biblioteca);
                    break;
                case 10:
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    public static void exibirMenu() {
        System.out.println("\n\t\t===== MENU =====");
        System.out.println("1 - Cadastrar Item");
        System.out.println("2 - Consultar ao acervo");
        System.out.println("3 - Cadastrar usuário");
        System.out.println("4 - Editar campo do usuário");
        System.out.println("5 - Empréstimo de exemplar");
        System.out.println("6 - Devolução de exemplar");
        System.out.println("7 - Emitir relatório de leitura do usuário");
        System.out.println("8 - Emitir relatório geral de empréstimos e devoluçôes");
        System.out.println("9 - Mostrar ranking de obras lidas(apenas crianças)");
        System.out.println("10 - Sair");
        System.out.print("Digite sua opção: ");
    }

    public static void cadastrarItem(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("\nEscolha o qual exemplar deseja cadastrar (livro, revista, hq ou manga): ");
        ArrayList<String> autores = new ArrayList<>();
        String tipo = scanner.nextLine();

        if(tipo.equalsIgnoreCase("livro") || tipo.equalsIgnoreCase("revista") ||
                tipo.equalsIgnoreCase("hq") || tipo.equalsIgnoreCase("manga")){
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Digite a quantidade de autores do exemplar: ");
            int qtd = scanner.nextInt();
            scanner.nextLine();
            for(int i =0; i<qtd; i++){
                System.out.printf("%d autor: ", i+1);
                String autoresStr = scanner.nextLine();
                autores.add(autoresStr);
            }
            System.out.print("Resumo: ");
            String resumo = scanner.nextLine();
            System.out.print("Editora: ");
            String editora = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            System.out.print("Número de Páginas: ");
            int numPaginas = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            if (tipo.equalsIgnoreCase("livro")) {
                Exemplar novoLivro = new Livro(titulo, autores, resumo, editora, ano, numPaginas, genero);
                biblioteca.cadastrarExemplar(novoLivro);
            } else if (tipo.equalsIgnoreCase("revista")) {
                System.out.print("Volume: ");
                int volume = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                System.out.print("Mês da Publicação: ");
                String mesPublicacao = scanner.nextLine();

                Exemplar novaRevista = new Revista(titulo, autores, resumo, editora, ano, numPaginas, genero, volume, mesPublicacao);
                biblioteca.cadastrarExemplar(novaRevista);
            }
            else if(tipo.equalsIgnoreCase("hq")){
                Exemplar novoHq = new Hqs(titulo, autores, resumo, editora, ano, numPaginas, genero);
                biblioteca.cadastrarExemplar(novoHq);
            }
            else if(tipo.equalsIgnoreCase("manga")){
                System.out.print("Tipo: ");
                String type = scanner.nextLine();
                Exemplar novoManga = new Manga(titulo, autores, resumo, editora, ano, numPaginas, genero, type);
                biblioteca.cadastrarExemplar(novoManga);
            }

        }
        else{
            System.out.println("VOCÊ DIGITOU UMA OPÇÃO QUE NÃO EXISTE.");
        }
    }

    public static void consultarAcervo(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("\n1 - consultar apenas um exemplar\n2 - Consultar todos os exemplares\nDigite sua opção: ");
        int op = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (op == 1) {
            System.out.print("Digite o titulo, o autor ou resumo do exemplar: ");
            String busca = scanner.nextLine();
            Exemplar ex = biblioteca.consultarExemplar(busca);
            if(ex != null){
                System.out.println(ex);
            }
            else{
                System.out.println("NENHUM EXEMPLAR FOI ENCONTRADO.");
            }
        } else if (op == 2) {
            System.out.print("Digite o titulo, o autor ou resumo do exemplar: ");
            String busca = scanner.nextLine();
            ArrayList<Exemplar> resultados = biblioteca.consultarTodos(busca);

            System.out.println("\n" + resultados.size() + " resultados encontrados ->->->");
            if (!resultados.isEmpty()) {
                for (Exemplar ex : resultados) {
                    System.out.println(ex);
                }
            }
        }
        else {
            System.out.println("entrada inválida!!!");
        }
    }

    public static void cadastrarUsuario(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("CPF do usuário: ");
        String cpfUsuario = scanner.nextLine();
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("É adulto ou criança?: ");
        String adultoOrCrianca = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, dataNascimento, cpfUsuario, adultoOrCrianca);
        biblioteca.cadastrarUsuario(novoUsuario);
    }

    public static void editarUsuario(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Digite o CPF do usuário que deseja editar: ");
        String cpf = scanner.nextLine();
        boolean veriCPF = biblioteca.verificarUsuario(cpf);
        if(veriCPF){
            System.out.println("\nInsira novamento todos os items, e corrija os que deseja mudar.");
            System.out.print("Insira o nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Data de Nascimento (DD/MM/AAAA): ");
            String novaDataNascimento = scanner.nextLine();
            System.out.print("adulto ou criança?: ");
            String adultoOCrianca = scanner.nextLine();

            Usuario edit = biblioteca.editarUsuario(cpf, novoNome, novaDataNascimento, adultoOCrianca);
            if (edit != null) {
                System.out.println("Usuário editado com sucesso\n");
                System.out.println(edit);
            } else {
                System.out.println("Erro ao editar usuário");
            }
        }
        else{
            System.out.println("CPF NÃO ENCONTRADO!");
        }
    }

    public static void emprestimoExemplar(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Digite o titulo do exemplar para ser emprestado: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o cpf da pessoa que vai adquirir o exemplar: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a data do empréstimo (DD/MM/YYYY): ");
        String dataEmprestimo = scanner.nextLine();
        LocalDate date = LocalDate.parse(dataEmprestimo, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        boolean emp = biblioteca.registrarEmprestimo(titulo, cpf, date);
        if(emp){
            System.out.println("EMPRÉSTIMO REALIZADO COM SUCESSO!");
            System.out.println("AVISO: limite máximo para devolução de 7 dias");
        }
        else{
            System.out.println("ERRO AO REALIZAR EMPRÉSTIMO!");
        }
    }

    public static void devolucaoExemplar(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Digite o cpf da pessoa que deseja devolver: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a data da devolução (DD/MM/YYYY): ");
        String data = scanner.nextLine();
        System.out.print("O usuário leu a obra (sim/não): ");
        String l = scanner.nextLine();
        LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double dev = biblioteca.realizarDevolucao(cpf, date, l);
        if(dev > 0.0){
            System.out.println("EXEMPLAR DEVOLVIDO COM SUCESSO!");
            System.out.printf("IMPORTANTE: O usuário ultrapassou os 7 dias para devolver por tanto receberá uma multa no valor de %.2fR$!\n", dev);
        }
        else if(dev == 0.0){
            System.out.println("EXEMPLAR DEVOLVIDO COM SUCESSO, VOLTE SEMPRE.");
        }
        else{
            System.out.println("OCORREU UM ERRO AO DEVOLVER O EXEMPLAR!");
        }
    }

    public static void emitirRelatorioLeitura(Biblioteca biblioteca, Scanner scanner){
        System.out.print("Digite o cpf do usuário para emitir o relatório: ");
        String cpf = scanner.nextLine();
        if(biblioteca.verificarUsuario(cpf)){
            ArrayList<Exemplar> lidos = biblioteca.relatorioLeituraUser(cpf);
            System.out.println("Total de livros lidos: " + lidos.size() + "\n");
            if (!lidos.isEmpty()) {
                for (Exemplar ex : lidos) {
                    System.out.println(ex);
                }
            }
        }
        else{
            System.out.println("Esse cpf não existe no banco de dados!");
        }
    }

    public static void relatorioGeralDeEmpEDev(Biblioteca biblioteca){
        ArrayList<Movimentacao> geral = biblioteca.relatorioGeral();
        if (!geral.isEmpty()) {
            System.out.println("\n-> Empréstimos: ");
            for (Movimentacao mov: geral) {
                if (mov instanceof Emprestimos) {
                    System.out.println("Usuário: " + mov.getUsuario().getNome());
                    System.out.println("\t\t---Exemplar---" + mov.getExemplar());
                }
                System.out.println();
            }
            System.out.println("-> Devoluções: ");
            for(Movimentacao mov: geral){
                if(mov instanceof Devolucao){
                    System.out.println("Usuário: " + mov.getUsuario().getNome());
                    System.out.println("\t\t---Exemplar---" + mov.getExemplar());
                }
                System.out.println();
            }
        }
        else{
            System.out.println("Ainda não foram feitos empréstimos nem devoluções!");
        }
    }

    public static void rankingMaisLidosCrianca(Biblioteca biblioteca){
        ArrayList<Usuario> ranking = biblioteca.relatorioRanking();
        int pos = 1;
        if(!ranking.isEmpty()){
            for(Usuario r: ranking){
                if(r!=null){
                    System.out.println(pos + " lugar: \n" + "Nome: " + r.getNome() + "\nQuantidade de obras lidas: " + r.getObrasLidas());
                    System.out.println();
                    pos++;
                }
            }
        }
        else{
            System.out.println("não há ranking até o momento.");
        }
    }
}
