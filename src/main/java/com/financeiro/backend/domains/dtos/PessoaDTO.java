package com.financeiro.backend.domains.dtos;

import com.financeiro.backend.domains.Pessoa;

public class PessoaDTO {

    private Long id;
    private String razaoSocial;
    private String email;
    private String senha;

    public PessoaDTO() { }

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.razaoSocial = pessoa.getRazaoSocial();
        this.email = pessoa.getEmail();
        this.senha = pessoa.getSenha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
