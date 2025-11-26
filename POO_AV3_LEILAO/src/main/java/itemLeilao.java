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

    public int gerarId() throws IOException {
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("itens.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            }
        }

        return maiorId + 1;
    }

    public ArrayList<itemLeilao> encerrarLeilao(Leilao leilaoAtual, Lance lanceAtual) throws IOException {
        ArrayList<itemLeilao> itens = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("itens.txt"));
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] dados = linha.split(";");
            int idLeilao = Integer.parseInt(dados[1]);

            if(idLeilao == leilaoAtual.getIdLeilao()){
                itemLeilao item = new itemLeilao();

                item.setIdItem(Integer.parseInt(dados[0]));
                item.setLeilao(leilaoAtual);
                item.setDescricaoItem(dados[2]);
                item.setLanceMinimo(Double.parseDouble(dados[3]));
                item.setItemArrematado(Boolean.parseBoolean(dados[4]));
                item.setLanceArrematante(lanceAtual);

                itens.add(item);
                item.mostrar();
            }


        }
        return null;
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
            }
        }

        bufferedReader.close();
        return listaItens;
    }

    public itemLeilao criarItem(int idItem) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("itens.txt"));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            int idItemSalvo = Integer.parseInt(dados[0]);
            int idLeilaoSalvo = Integer.parseInt(dados[1]);
            if(idItemSalvo == idItem){
                itemLeilao item = new itemLeilao();
                Leilao leilao = new Leilao();
                item.setIdItem(idItem);
                item.setLeilao(leilao.criarLeilao(idLeilaoSalvo));
                item.setDescricaoItem(dados[2]);
                item.setLanceMinimo(Double.valueOf(dados[3]));
                item.setItemArrematado(Boolean.parseBoolean(dados[4]));
                return item;
            }
        }
        br.close();
        return null;
    }


    public void mostrar(){
        System.out.println("ID: " + this.idItem);
        System.out.println("Descricao: " + this.descricaoItem);
        System.out.println("Lance mínimo: " + this.lanceMinimo);
        System.out.println("Item: " + this.itemArrematado);
        System.out.println("Lance arrematante: " + this.lanceArrematante);
    }

}

