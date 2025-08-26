package com.financeiro.backend.domains.enums;

public enum TipoPessoa {
    ADMIN(0,"ADMIN"),CLIENTE(1,"CLIENTE");

    private Integer id;
    private String tipo;

    TipoPessoa(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static TipoPessoa toEnum(Integer id){
        if(id==null) return null;
        for(TipoPessoa x : TipoPessoa.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status Invalido");
    }
}
