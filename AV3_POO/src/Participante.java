import java.io.*;

public class Participante {
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String emailParticipante;
    private String senhaParticipante;
    private String telefoneParticipante;

    public Participante(int idParticipante,
                        String nomeParticipante,
                        String loginParticipante,
                        String emailParticipante,
                        String senhaParticipante,
                        String telefoneParticipante)
    {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.loginParticipante = loginParticipante;
        this.emailParticipante = emailParticipante;
        this.senhaParticipante = senhaParticipante;
        this.telefoneParticipante = telefoneParticipante;
    }


    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public int idParticipante() {
        return idParticipante;
    }

    public Participante setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
        return this;
    }

    public String loguinParticipante() {
        return loginParticipante;
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

    public String telefoneParticipante() {
        return telefoneParticipante;
    }

    public void setTelefoneParticipante(String telefoneParticipante) {
        this.telefoneParticipante = telefoneParticipante;
    }

    public Participante loginParticipante() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("participantes.txt"));
        String linha = bufferedReader.readLine();
        while (linha != null) {
            if(linha.contains(this.loginParticipante) && linha.contains(this.senhaParticipante)) {
                String[] dados = linha.split(";");
                return new Participante(Integer.parseInt(dados[0]),
                                       dados[1],
                                       dados[2],
                                       dados[3],
                                       dados[4],
                                       dados[5]);
            }
            linha = bufferedReader.readLine();
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
        }
    }

    //public void listarParticipantes() {}



}
