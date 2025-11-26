import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Lance {
    private int idLance;
    private Participante participante;
    private itemLeilao itemLeilao;
    private double valorlance;
    private String dataLance;
    private String horasLance;



    public Lance(int idLance, Participante participante, itemLeilao itemLeilao, String dataLance, String horasLance) {
        this.idLance = idLance;
        this.participante = participante;
        this.itemLeilao = itemLeilao;
        this.dataLance = dataLance;
        this.horasLance = horasLance;
    }

    public Lance() {}

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
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("lances.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");

                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            }
        }

        return maiorId + 1;
    }

    public Lance buscarMaiorLanceDoItem(int idItem) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("lances.txt"));
        String linha;
        Lance maior = null;

        while ((linha = br.readLine()) != null) {

            String[] dados = linha.split(";");

            int item = Integer.parseInt(dados[1]);
            double valor = Double.parseDouble(dados[3]);

            if (item == idItem) {
                Participante participante = new Participante();
                itemLeilao itemLeilao = new itemLeilao();
                Lance lance = new Lance();
                lance.setIdLance(Integer.parseInt(dados[0]));
                lance.setItemLeilao(itemLeilao.criarItem(Integer.parseInt(dados[1])));
                lance.setParticipante(participante.criarParticipante(Integer.parseInt(dados[2])));
                lance.setValorlance(valor);
                lance.setDataLance(dados[4]);
                lance.setHorasLance(dados[5]);

                if (maior == null || valor > maior.getValorlance()) {
                    maior = lance;
                }
            }
        }

        br.close();
        System.out.println(maior);
        return maior;
    }


    public ArrayList<Lance> listarLances(Participante participanteAtual) throws IOException {

        ArrayList<Lance> listaLances = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("lances.txt"));
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {

            String[] dados = linha.split(";");

            int idParticipanteNoLance = Integer.parseInt(dados[2]);


            if (idParticipanteNoLance == participanteAtual.getIdParticipante()) {

                Lance lance = new Lance();
                lance.setIdLance(Integer.parseInt(dados[0]));
                lance.setParticipante(participanteAtual);
                lance.setValorlance(Double.parseDouble(dados[3]));
                lance.setDataLance(dados[4]);
                lance.setHorasLance(dados[5]);

                listaLances.add(lance);
                lance.mostrar();
            }
        }

        bufferedReader.close();
        return listaLances;
    }


    public void mostrar(){
        System.out.println("ID: " + this.idLance);
        System.out.println("Participante: " + this.participante.getIdParticipante());
        System.out.println("Valor: " + this.valorlance);
        System.out.println("Data: " + this.dataLance);
        System.out.println("Horas: " + this.horasLance);
    }

}

