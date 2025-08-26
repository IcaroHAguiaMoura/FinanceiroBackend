package com.financeiro.backend.domains.enums;

public enum TipoConta {

    CONTA_CORRENTE(0,"CONTA_CORRENTE"),CONTA_INVESTIMENTO(1,"CONTA_INVESTIMENTO"),
    CARTAO_DE_CREDITO(2,"CARTAO_DE_CREDITO"), ALIMENTACAO(3,"ALIMENTACAO"), POUPANCA(4,"POUPANCA");

    private Integer id;
    private String tipoConta;

    TipoConta(Integer id, String tipoConta) {
        this.id = id;
        this.tipoConta = tipoConta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    private static TipoConta toEnum(Integer id){
        if(id==null) return null;
        for(TipoConta x : TipoConta.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("TipoConta invalido");
    }
}
