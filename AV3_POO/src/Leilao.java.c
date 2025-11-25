public class Leilao {
    private int idLeilao;
    private String dataInicioLeilao;
    private String dataFimLeilao;
    private String horaInicioLeilao;
    private String horaFimLeilao;
    private boolean statusLeilao;

    public Leilao(int idLeilao,
                  String dataInicioLeilao,
                  String dataFimLeilao,
                  String horaInicioLeilao,
                  String horaFimLeilao,
                  boolean statusLeilao)
    {
        this.idLeilao = idLeilao;
        this.dataInicioLeilao = dataInicioLeilao;
        this.dataFimLeilao = dataFimLeilao;
        this.horaInicioLeilao = horaInicioLeilao;
        this.horaFimLeilao = horaFimLeilao;
        this.statusLeilao = statusLeilao;
    }

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


}
