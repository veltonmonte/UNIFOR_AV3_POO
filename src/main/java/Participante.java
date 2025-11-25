import java.io.*;

public class Participante {
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String emailParticipante;
    private String senhaParticipante;
    private String telefoneParticipante;

    public Participante() {}

    public int getIdParticipante() {
        return idParticipante;
    }

    public String getSenhaParticipante() {
        return senhaParticipante;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public String getLoginParticipante() {
        return loginParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public void setLoginParticipante(String loginParticipante) {
        this.loginParticipante = loginParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public void setEmailParticipante(String emailParticipante) {
        this.emailParticipante = emailParticipante;
    }

    public String senhaParticipante() {
        return senhaParticipante;
    }

    public void setSenhaParticipante(String senhaParticipante) {
        this.senhaParticipante = senhaParticipante;
    }

    public String getTelefoneParticipante() {
        return telefoneParticipante;
    }

    public void setTelefoneParticipante(String telefoneParticipante) {
        this.telefoneParticipante = telefoneParticipante;
    }


    public Participante loginParticipante(String login, String senha) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("participantes.txt"))) {
            String linha;


            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");


                String loginArquivo = dados[2];
                String senhaArquivo = dados[4];

                if (loginArquivo.equals(login) && senhaArquivo.equals(senha)) {
                    Participante participante = new Participante();

                    participante.setIdParticipante(Integer.parseInt(dados[0]));
                    participante.setNomeParticipante(dados[1]);
                    participante.setLoginParticipante(dados[2]);
                    participante.setEmailParticipante(dados[3]);
                    participante.setSenhaParticipante(dados[4]);
                    participante.setTelefoneParticipante(dados[5]);

                    System.out.println("Login participante: " + participante.getLoginParticipante());

                    return participante;

                }
            }

            System.out.println("DADOS INVALIDOS!!!");


        }
        return null;
    }



    public void registrarParticipante() throws IOException {
        FileWriter fileWriter = new FileWriter("participantes.txt", true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(this.idParticipante + ";" +
                    this.nomeParticipante + ";" +
                    this.loginParticipante + ";" +
                    this.emailParticipante + ";" +
                    this.senhaParticipante + ";" +
                    this.telefoneParticipante);

            bufferedWriter.newLine();
            bufferedWriter.close();
        }
    }

    public int gerarId() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("participantes.txt"));
        String linha;

        int utimoId = 0;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            utimoId = Integer.parseInt(dados[0]);
        }

        return utimoId + 1;

    }

    //public void listarParticipantes() {}

}