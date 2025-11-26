import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaLeilao {

    private Scanner scanner = new Scanner(System.in);

    public Participante iniciar() throws IOException {
        System.out.println("Você deseja registrar um novo participante? (s/n)");
        String resposta = scanner.nextLine().trim();

        if (resposta.equalsIgnoreCase("s")) {
            return registrar();
        } else {

            Participante p = login();

            while (p == null) {
                System.out.println("Tente novamente.");
                p = login();

            }
            return p;
        }
    }


    private Participante registrar() throws IOException {
        String nome;
        String login;
        String email;
        String telefone;
        String senha;
        boolean valido = false;
        while (!valido) {
            System.out.println("Digite o nome do participante:");
            nome = scanner.nextLine();

            System.out.println("Digite o login do participante:");
            login = scanner.nextLine();

            System.out.println("Digite o email do participante:");
            email = scanner.nextLine();

            System.out.println("Digite o telefone do participante:");
            telefone = scanner.nextLine();

            System.out.println("Digite a senha do participante:");
            senha = scanner.nextLine();


            if (new Participante().verificarParticipante(login, email)) {
                System.out.println("Erro: já existe um participante com esse login ou email!");
            }else {
                Participante participanteAtual = new Participante();
                participanteAtual.setIdParticipante(participanteAtual.gerarId());
                participanteAtual.setNomeParticipante(nome);
                participanteAtual.setLoginParticipante(login);
                participanteAtual.setEmailParticipante(email);
                participanteAtual.setSenhaParticipante(senha);
                participanteAtual.setTelefoneParticipante(telefone);
                try {
                    participanteAtual.registrarParticipante();
                    System.out.println("Participante registrado com sucesso!");
                } catch (Exception e) {
                    System.err.println("Erro ao registrar participante: " + e.getMessage());
                }
                valido = true;
                return participanteAtual;
            }

        }
        return null;
    }

    public Participante login() throws IOException {
        System.out.println("Digite seu login:");
        String login = scanner.nextLine().trim();

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine().trim();

        Participante p = new Participante().loginParticipante(login, senha);

        if (p == null) {
            System.out.println("Login inválido!");
        }

        return p;
    }

    public void comecarLeilao(Leilao leilaoAtual, itemLeilao itemAtual) throws IOException {

        System.out.println("Digite a data de inicio do leilao: ");
        String dataInicioLeilao = scanner.nextLine();

        System.out.println("Digite a data de fim do leilao: ");
        String dataFimLeilao = scanner.nextLine();

        System.out.println("Leilao iniciado com sucesso!");


        leilaoAtual.setDataInicioLeilao(dataInicioLeilao);
        leilaoAtual.setDataFimLeilao(dataFimLeilao);
        leilaoAtual.setHoraInicioLeilao("12:00");
        leilaoAtual.setHoraFimLeilao("12:00");
        leilaoAtual.setStatusLeilao(true);


        int id = leilaoAtual.gerarId();
        leilaoAtual.setIdLeilao(id);

        leilaoAtual.registrarLeilao();

        System.out.println("Você precisa registrar um item no seu leilão agora");

        System.out.println("Digite a descrição do item: ");
        String descricaoItem = scanner.nextLine();

        System.out.println("Digite o lance mínimo do leilao: ");
        String valor = scanner.nextLine().replace(",", ".");
        double lanceMinimo = Double.parseDouble(valor);


        itemAtual.setLanceMinimo(lanceMinimo);
        itemAtual.setLeilao(leilaoAtual);
        itemAtual.setDescricaoItem(descricaoItem);
        itemAtual.setItemArrematado(false);
        int iditem = itemAtual.gerarId();
        itemAtual.setIdItem(iditem);
        itemAtual.setLeilao(leilaoAtual);
        itemAtual.setLanceMinimo(lanceMinimo);
        itemAtual.setLanceArrematante(null);

        itemAtual.registrarItemLeilao(leilaoAtual);

        boolean processoConcluido = false;

        while (processoConcluido == false) {
            System.out.println("Deseja Adicionar Mais Um Item? (s/n)");
            String resposta = scanner.nextLine().trim();
            if (resposta.equalsIgnoreCase("s")) {
                System.out.println("Digite a descrição do item: ");
                descricaoItem = scanner.nextLine();

                System.out.println("Digite o lance mínimo do leilao: ");
                valor = scanner.nextLine().replace(",", ".");
                lanceMinimo = Double.parseDouble(valor);

                itemAtual.setLanceMinimo(lanceMinimo);
                itemAtual.setLeilao(leilaoAtual);
                itemAtual.setDescricaoItem(descricaoItem);
                itemAtual.setItemArrematado(false);
                iditem = itemAtual.gerarId();
                itemAtual.setIdItem(iditem);
                itemAtual.setLeilao(leilaoAtual);
                itemAtual.setLanceMinimo(lanceMinimo);
                itemAtual.setLanceArrematante(null);

                itemAtual.registrarItemLeilao(leilaoAtual);
            } else if (resposta.equalsIgnoreCase("n")) {
                processoConcluido = true;
            } else {
                System.out.println("Erro ao adicionar Mais Um Item");
            }
        }
    }

    public void consultarLeilao(Participante participanteAtual, Leilao leilaoAtual, itemLeilao itemAtual, Lance lanceAtual) throws IOException {

        leilaoAtual.listarLeiloes();
        System.out.println("Digite o id do Leilão: ");
        int idLeilao = scanner.nextInt();

        leilaoAtual.mostrar(idLeilao);
        leilaoAtual.setIdLeilao(idLeilao);

        System.out.println("Deseja ver os itens do leilão? (s/n)");
        String verItens = scanner.next();

        if (verItens.equals("s")) {

            itemLeilao itemEscolhido = null;
            ArrayList<itemLeilao> itens = itemAtual.listarItens(leilaoAtual);

            for (itemLeilao item : itens) {
                item.mostrar();
            }


            System.out.println("Deseja dar uma lance? (s/n)");
            String desejaLance = scanner.next();

            if (desejaLance.equals("s")) {

                System.out.println("Digite o ID do item que deseja dar o lance:");
                int idItemEscolhido = scanner.nextInt();
                scanner.nextLine();


                for (itemLeilao item : itens) {
                    if (item.getIdItem() == idItemEscolhido) {
                        itemEscolhido = item;
                        break;
                    }
                }

                if (itemEscolhido != null) {
                    System.out.println("Item encontrado:");
                    itemEscolhido.mostrar();
                } else {
                    System.out.println("Item não encontrado!");
                }

                boolean lanceValido = false;

                while (lanceValido == false) {
                    System.out.println("Digite seu lance >= lance mínimo: ");
                    double lanceConcorrente = scanner.nextDouble();
                    scanner.nextLine();

                    if (lanceConcorrente >= itemEscolhido.getLanceMinimo()) {

                        System.out.println("Digite a data do seu lance:");
                        String dataDoLance = scanner.nextLine();

                        System.out.println("Digite a hora do seu lance:");
                        String horaDoLance = scanner.nextLine();

                        if (participanteAtual == null) {
                            System.out.println("Erro: participante não identificado.");
                            return;
                        }

                        System.out.println("Lance realizado com sucesso!");

                        lanceAtual.setIdLance(lanceAtual.gerarId());
                        lanceAtual.setParticipante(participanteAtual);
                        lanceAtual.setValorlance(lanceConcorrente);
                        lanceAtual.setDataLance(dataDoLance);
                        lanceAtual.setHorasLance(horaDoLance);

                        lanceAtual.setItemLeilao(itemEscolhido);

                        lanceAtual.registrarLance();
                        lanceValido = true;
                    } else {
                        System.out.println("O seu lance nao é válido");
                    }
                }
            }
        }
    }

    public void mostrarLances(Lance lanceAtual, Participante participanteAtual) throws IOException {
        lanceAtual.listarLances(participanteAtual);
    }

    public void encerrarLeilao(Leilao leilaoAtual) throws IOException {

        leilaoAtual.listarLeiloes();

        System.out.println("Qual leilão você deseja encerrar? Digite o ID:");
        int idLeilao = scanner.nextInt();

        BufferedReader br = new BufferedReader(new FileReader("itens.txt"));
        String linha;

        while ((linha = br.readLine()) != null) {

            String[] dados = linha.split(";");
            int idLeilaoDoItem = Integer.parseInt(dados[1]);
            int idItem = Integer.parseInt(dados[0]);
            if (idLeilaoDoItem == idLeilao) {

                itemLeilao item = new itemLeilao();
                item = item.criarItem(idItem);

                Lance lance = new Lance();

                Lance maior = lance.buscarMaiorLanceDoItem(idItem);

                if (maior != null) {
                    item.setLanceArrematante(maior);
                    System.out.println("Item: " + item.getDescricaoItem());
                    System.out.println("Maior lance: R$ " + maior.getValorlance());
                    System.out.println("Vencedor: " + maior.getParticipante().getNomeParticipante());
                    System.out.println("Lance arrematante: " + item.getLanceArrematante().getValorlance());
                    System.out.println();
                } else {
                    System.out.println("Item: " + item.getDescricaoItem());
                    System.out.println("Nenhum lance foi dado para este item.");
                    System.out.println();
                }
            }
        }

        br.close();
    }



}