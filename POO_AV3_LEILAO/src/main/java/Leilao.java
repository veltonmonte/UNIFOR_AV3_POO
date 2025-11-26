import java.io.*;
import java.util.ArrayList;

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

    public void encerrarLeilao(Leilao leilaoAtual) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("leiloes.txt"));
        leilaoAtual.setStatusLeilao(false);
    }

    public int gerarId() throws IOException {
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("leiloes.txt"))) {
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

    public Leilao criarLeilao(int idLeilao) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("leiloes.txt"));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            int idLeilaoSalvo = Integer.parseInt(dados[0]);
            if(idLeilaoSalvo == idLeilao){
                Leilao leilao = new Leilao();
                leilao.setIdLeilao(idLeilao);
                leilao.setDataInicioLeilao(dados[1]);
                leilao.setDataFimLeilao(dados[2]);
                leilao.setHoraInicioLeilao(dados[3]);
                leilao.setHoraFimLeilao(dados[4]);
                leilao.setStatusLeilao(Boolean.parseBoolean(dados[5]));
                return leilao;
            }
        }
        br.close();
        return null;
    }

    public ArrayList<Leilao> listarLeiloes() throws IOException {
        ArrayList<Leilao> listaItens = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("leiloes.txt"));
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {
            String[] dados = linha.split(";");


                Leilao leilao = new Leilao();
                leilao.setIdLeilao(Integer.parseInt(dados[0]));
                leilao.setDataInicioLeilao(dados[1]);
                leilao.setDataFimLeilao(dados[2]);
                leilao.setHoraInicioLeilao(dados[3]);
                leilao.setStatusLeilao(Boolean.parseBoolean(dados[4]));
                leilao.setHoraFimLeilao(dados[5]);

                listaItens.add(leilao);
                leilao.mostrar(Integer.parseInt(dados[0]));
        }

        bufferedReader.close();
        return listaItens;
    }



    public void mostrar(int idLeilao) throws IOException {
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


                System.out.println("____________________________________________________");
                System.out.println("ID:"+leilao.getIdLeilao());
                System.out.println("Data do Começo: "+leilao.getDataInicioLeilao());
                System.out.println("Data do Fim: "+leilao.getDataFimLeilao());
                System.out.println("Hora Inicio: "+leilao.getHoraInicioLeilao());
                System.out.println("Hora Fim: "+leilao.getHoraFimLeilao());
                System.out.println("Status: "+leilao.getStatusLeilao());
                System.out.println("____________________________________________________");
            }
        }
    }
}
