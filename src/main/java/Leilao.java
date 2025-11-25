package main.java;

import java.io.*;

public class Leilao {
    private int idLeilao;
    private String dataInicioLeilao;
    private String dataFimLeilao;
    private String horaInicioLeilao;
    private String horaFimLeilao;
    private boolean statusLeilao;

    public Leilao(){}

    public int getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(int idLeilao) {
        this.idLeilao = idLeilao;
    }

    public String getDataInicioLeilao() {
        return dataInicioLeilao;
    }

    public void setDataInicioLeilao(String dataInicioLeilao) {
        this.dataInicioLeilao = dataInicioLeilao;
    }

    public String getDataFimLeilao() {
        return dataFimLeilao;
    }

    public void setDataFimLeilao(String dataFimLeilao) {
        this.dataFimLeilao = dataFimLeilao;
    }

    public String getHoraInicioLeilao() {
        return horaInicioLeilao;
    }

    public void setHoraInicioLeilao(String horaInicioLeilao) {
        this.horaInicioLeilao = horaInicioLeilao;
    }

    public String getHoraFimLeilao() {
        return horaFimLeilao;
    }

    public boolean getStatusLeilao() {
        return statusLeilao;
    }

    public void setStatusLeilao(boolean statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    public void setHoraFimLeilao(String horaFimLeilao) {
        this.horaFimLeilao = horaFimLeilao;
    }

    public void registrarLeilao() throws IOException {
        FileWriter fileWriter = new FileWriter("leiloes.txt", true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(this.idLeilao + ";" +
                    this.dataInicioLeilao + ";" +
                    this.dataFimLeilao + ";" +
                    this.horaInicioLeilao + ";" +
                    this.horaFimLeilao + ";" +
                    this.statusLeilao);

            bufferedWriter.newLine();
            bufferedWriter.close();
        }
    }

    public int gerarId() throws IOException {
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("leiloes.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue; // ignora linhas vazias

                String[] dados = linha.split(";");

                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            }
        }

        return maiorId + 1;
    }

    public Leilao listarLeilao(int idLeilao) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("leiloes.txt"));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (idLeilao == Integer.parseInt(dados[0])) {
                Leilao leilao = new Leilao();
                leilao.setIdLeilao(Integer.parseInt(dados[0]));
                leilao.setDataInicioLeilao(dados[1]);
                leilao.setDataFimLeilao(dados[2]);
                leilao.setHoraInicioLeilao(dados[3]);
                leilao.setHoraFimLeilao(dados[4]);
                leilao.setStatusLeilao(true);
                leilao.mostrar();
                return leilao;
            }
        }

        return null;
    }

    public void mostrar(){
        System.out.println("Id: " + this.idLeilao);
        System.out.println("Data inicio: " + this.dataInicioLeilao);
        System.out.println("Data fim: " + this.dataFimLeilao);
        System.out.println("Hora inicio: " + this.horaInicioLeilao);
        System.out.println("Hora fim: " + this.horaFimLeilao);
        System.out.println("Status: " + this.statusLeilao);
    }


}
