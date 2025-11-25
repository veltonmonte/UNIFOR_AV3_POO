package main.java;

import java.io.*;

public class Lance {
    private int idLance;
    private Participante participante;
    private itemLeilao itemLeilao;
    private double valorlance;
    private String dataLance;
    private String horasLance;



    public Lance(){}

    public double getValorlance() {
        return valorlance;
    }

    public void setValorlance(double valorlance) {
        this.valorlance = valorlance;
    }

    public int getIdLance() {
        return idLance;
    }

    public void setIdLance(int idLance) {
        this.idLance = idLance;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public itemLeilao getItemLeilao() {
        return itemLeilao;
    }

    public void setItemLeilao(itemLeilao itemLeilao) {
        this.itemLeilao = itemLeilao;
    }

    public String getDataLance() {
        return dataLance;
    }

    public void setDataLance(String dataLance) {
        this.dataLance = dataLance;
    }

    public String getHorasLance() {
        return horasLance;
    }

    public void setHorasLance(String horasLance) {
        this.horasLance = horasLance;
    }

    public boolean registrarLance() throws IOException {

        FileWriter fileWriter = new FileWriter("lances.txt", true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(
                    this.idLance + ";" +
                    getItemLeilao().getIdItem() + ";" +
                    getParticipante().getIdParticipante() + ";" +
                    this.valorlance + ";" +
                    this.dataLance + ";" +
                    this.horasLance
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        }catch(IOException e){
            return false;
        }
    }

    public int gerarId() throws IOException {
        int utimoId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("lances.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue; // ignora linhas vazias

                String[] dados = linha.split(";");

                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual > utimoId) {
                    utimoId = idAtual;
                }
            }
        }

        return utimoId + 1;
    }

}

