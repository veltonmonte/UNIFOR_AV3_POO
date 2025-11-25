package main.java;

import java.io.*;
import java.util.ArrayList;

public class itemLeilao {
    private int idItem;
    private Leilao leilao;
    private String descricaoItem;
    private Double lanceMinimo;
    private boolean itemArrematado;
    private Lance lanceArrematante;

    public itemLeilao(){}

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Double getLanceMinimo() {
        return lanceMinimo;
    }

    public void setLanceMinimo(Double lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public boolean isItemArrematado() {
        return itemArrematado;
    }

    public void setItemArrematado(boolean itemArrematado) {
        this.itemArrematado = itemArrematado;
    }

    public Lance getLanceArrematante() {
        return lanceArrematante;
    }

    public void setLanceArrematante(Lance lanceArrematante) {
        this.lanceArrematante = lanceArrematante;
    }

    public void registrarItemLeilao(Leilao leilao) throws IOException {
        FileWriter fileWriter = new FileWriter("itens.txt", true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(this.idItem + ";" +
                   leilao.getIdLeilao() + ";" +
                    this.descricaoItem + ";" +
                    this.lanceMinimo + ";" +
                    this.itemArrematado + ";" +
                    this.lanceArrematante);

            bufferedWriter.newLine();
            bufferedWriter.close();
    }catch (IOException e){
        e.printStackTrace();
        }
    }

    public int gerarIdItemPorLeilao(Leilao leilao) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("itens.txt"));
        String linha;
        int maiorId = 0;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");

            if (dados.length < 2) {
                continue;
            }

            int idLeilaoArquivo = Integer.parseInt(dados[1]);
            int idItemArquivo = Integer.parseInt(dados[0]);

            if (idLeilaoArquivo == leilao.getIdLeilao()) {
                if (idItemArquivo > maiorId) {
                    maiorId = idItemArquivo;
                }
            }
        }

        return maiorId + 1;
    }


    public ArrayList<itemLeilao> listarItens(Leilao leilaoAtual) throws IOException {
        ArrayList<itemLeilao> listaItens = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("itens.txt"));
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {
            String[] dados = linha.split(";");

            int idLeilao = Integer.parseInt(dados[1]);

            if (idLeilao == leilaoAtual.getIdLeilao()) {

                itemLeilao item = new itemLeilao();
                item.setIdItem(Integer.parseInt(dados[0]));
                item.setDescricaoItem(dados[1]);
                item.setDescricaoItem(dados[2]);
                item.setLanceMinimo(Double.parseDouble(dados[3]));
                setItemArrematado(Boolean.parseBoolean(dados[4]));
                setItemArrematado(Boolean.parseBoolean(dados[5]));

                listaItens.add(item);
                item.mostrar();
            }
        }

        bufferedReader.close();
        return listaItens;
    }


    public void mostrar(){
        System.out.println("ID: " + this.idItem);
        System.out.println("Descricao: " + this.descricaoItem);
        System.out.println("Lance mínimo: " + this.lanceMinimo);
        System.out.println("Item arrematado: " + this.itemArrematado);
        System.out.println("Lance arrematante: " + this.lanceArrematante);
    }

}

