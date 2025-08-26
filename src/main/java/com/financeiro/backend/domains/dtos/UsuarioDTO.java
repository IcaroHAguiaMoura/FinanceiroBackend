package com.financeiro.backend.domains.dtos;


import com.financeiro.backend.domains.Usuario;
import com.financeiro.backend.domains.enums.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Razão social é obrigatória")
    private String razaoSocial;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    private TipoPessoa tipoPessoa;

    public UsuarioDTO() {}


    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.razaoSocial = usuario.getRazaoSocial();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.tipoPessoa = usuario.getTipoPessoa();
    }


    public UsuarioDTO(Long id, String razaoSocial, String email, String senha, TipoPessoa tipoPessoa) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.senha = senha;
        this.tipoPessoa = tipoPessoa;
    }


    public Usuario toEntity() {
        return new Usuario(id, razaoSocial, email, senha, tipoPessoa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Razão social é obrigatória") String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotBlank(message = "Razão social é obrigatória") String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public @Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Senha é obrigatória") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "Senha é obrigatória") String senha) {
        this.senha = senha;
    }

    public @NotNull(message = "Tipo de pessoa é obrigatório") TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(@NotNull(message = "Tipo de pessoa é obrigatório") TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
