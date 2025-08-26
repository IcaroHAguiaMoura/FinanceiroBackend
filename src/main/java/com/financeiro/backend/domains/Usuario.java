package com.financeiro.backend.domains;

import com.financeiro.backend.domains.enums.TipoPessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario extends Pessoa {

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    public Usuario() { }

    public Usuario(Pessoa pessoa, TipoPessoa tipoPessoa) {
        super(pessoa.getRazaoSocial(), pessoa.getEmail(), pessoa.getSenha());
        this.tipoPessoa = tipoPessoa;
    }

    public Usuario(Long id, String razaoSocial, String email, String senha, TipoPessoa tipoPessoa) {
        super(razaoSocial, email, senha);
        this.setId(id);
        this.tipoPessoa = tipoPessoa;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    private void setId(Long id) { // usado para herança JOINED
        try {
            java.lang.reflect.Field field = Pessoa.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
