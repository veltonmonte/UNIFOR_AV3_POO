public class Lance {
    private int idLance;
    private Participante participante;
    private itemLeilao itemLeilao;
    private double valorlance;
    private String dataLance;
    private String horasLance;

    public Lance(int idLance,
                 Participante participante,
                 itemLeilao itemLeilao,
                 String dataLance,
                 String horasLance)
    {
        this.idLance = idLance;
        this.participante = participante;
        this.itemLeilao = itemLeilao;
        this.dataLance = dataLance;
        this.horasLance = horasLance;
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

}
