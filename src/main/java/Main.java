package main.java;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        SistemaLeilao sistemaLeilao = new SistemaLeilao();
        Participante participanteAtual = sistemaLeilao.iniciar();


        Leilao leilaoAtual = new Leilao();
        itemLeilao itemAtual = new itemLeilao();
        Lance lanceAtual = new Lance();

        while (true) {

            if (participanteAtual != null) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("_______________________");
                System.out.println("Você deseja:");
                System.out.println("1: Começar leilão");
                System.out.println("2: Consultar leilões");
                System.out.println("3: Ver Lances");
                System.out.println("4: Sair");
                System.out.println("_______________________");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        sistemaLeilao.comecarLeilao(leilaoAtual, itemAtual);
                        break;

                    case 2:
                        sistemaLeilao.consultarLeilao(participanteAtual ,leilaoAtual, itemAtual, lanceAtual);
                        break;

                    case 3:

                    case 4:
                        System.exit(0);

                }
            }
        }
    }
}