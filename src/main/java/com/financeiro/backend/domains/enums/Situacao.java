package com.financeiro.backend.domains.enums;

public enum Situacao {

    ABERTO(0,"ABERTO"), BAIXADO(1,"BAIXADO");

    private Integer id;
    private String tipoSituacao;

    Situacao(Integer id, String tipoSituacao) {
        this.id = id;
        this.tipoSituacao = tipoSituacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoSituacao() {
        return tipoSituacao;
    }

    public void setTipoSituacao(String tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    private static Situacao toEnum(Integer id){
        if(id==null) return null;
        for(Situacao x : Situacao.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Situacao invalida");
    }
}
