package main.java;

import java.io.IOException;
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
            Participante p = login();  // login correto agora
            while (p == null) {
                System.out.println("Tente novamente.");
                p = login();
            }
            return p;
        }
    }


    private Participante registrar() throws IOException {

        System.out.println("Digite o nome do participante:");
        String nome = scanner.nextLine();

        System.out.println("Digite o login do participante:");
        String login = scanner.nextLine();

        System.out.println("Digite o email do participante:");
        String email = scanner.nextLine();

        System.out.println("Digite a senha do participante:");
        String senha = scanner.nextLine();

        System.out.println("Digite o telefone do participante:");
        String telefone = scanner.nextLine();

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

        return participanteAtual;
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
        int iditem = itemAtual.gerarIdItemPorLeilao(leilaoAtual);
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
                iditem = itemAtual.gerarIdItemPorLeilao(leilaoAtual);
                itemAtual.setIdItem(iditem);
                itemAtual.setLeilao(leilaoAtual);
                itemAtual.setLanceMinimo(lanceMinimo);
                itemAtual.setLanceArrematante(null);

                itemAtual.registrarItemLeilao(leilaoAtual);
            } else if (resposta.equalsIgnoreCase("n")) {
                processoConcluido = true;
            } else  {
                System.out.println("Erro ao adicionar Mais Um Item");
            }
        }
    }

    public void consultarLeilao(Participante participanteAtual, Leilao leilaoAtual, itemLeilao itemAtual, Lance lanceAtual) throws IOException {
        System.out.println("Digite o id do Leilão: ");
        int idLeilao = scanner.nextInt();

        leilaoAtual.listarLeilao(idLeilao);
        leilaoAtual.setIdLeilao(idLeilao);

        System.out.println("Deseja ver os itens do leilão? (s/n)");
        String verItens = scanner.next();

        if (verItens.equals("s")) {

            System.out.println(itemAtual.listarItens(leilaoAtual));
            System.out.println("Deseja dar uma lance? (s/n)");
            String desejaLance = scanner.next();

            if (desejaLance.equals("s")) {

                System.out.println("Digite o ID do item que deseja dar o lance:");
                int idItemEscolhido = scanner.nextInt();
                scanner.nextLine();

                itemLeilao itemEscolhido = null;
                ArrayList<itemLeilao> itens = itemAtual.listarItens(leilaoAtual);


                for (itemLeilao item : itens) {
                    if (item.getIdItem() == idItemEscolhido) {
                        itemEscolhido = item;
                        break; // achou → sai do loop
                    }
                }

                if (itemEscolhido != null) {
                    System.out.println("Item encontrado:");
                    itemEscolhido.mostrar();
                } else {
                    System.out.println("Item não encontrado!");
                }

                System.out.println("Digite seu lance >= lance mínimo: ");
                double lanceConcorrente = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Digite a data do seu lance:");
                String dataDoLance = scanner.nextLine();

                System.out.println("Digite a hora do seu lance:");
                String horaDoLance = scanner.nextLine();

                if (lanceConcorrente >= itemEscolhido.getLanceMinimo()) {

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

                    // AQUI ESTAVA O ERRO → trocado itemAtual por itemEscolhido
                    lanceAtual.setItemLeilao(itemEscolhido);

                    lanceAtual.registrarLance();
                }
            }
        }
    }



}
