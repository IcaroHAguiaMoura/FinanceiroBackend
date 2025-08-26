package com.financeiro.backend.domains.enums;

public enum TipoLancamento {

    CREDITO(0,"CREDITO"), DEBITO(1,"DEBITO");

    private Integer id;
    private String tipoLancamento;

    TipoLancamento(Integer id, String tipoLancamento) {
        this.id = id;
        this.tipoLancamento = tipoLancamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    private static TipoLancamento toEnum(Integer id){
        if (id==null) return null;
        for(TipoLancamento x : TipoLancamento.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("TipoLancamento invalido");
    }
}
