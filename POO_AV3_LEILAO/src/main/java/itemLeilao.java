public class itemLeilao {
    private int idItem;
    private Leilao leilao;
    private String descricaoItem;
    private Double lanceMinimo;
    private boolean itemArrematado;
    private Lance lanceArrematante;

    public itemLeilao(int idItem,
                      Leilao leilao,
                      String descricaoItem,
                      Double lanceMinimo,
                      Lance lanceArrematante)
    {
        this.idItem = idItem;
        this.leilao = leilao;
        this.descricaoItem = descricaoItem;
        this.lanceMinimo = lanceMinimo;
        this.lanceArrematante = lanceArrematante;
    }

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

}

