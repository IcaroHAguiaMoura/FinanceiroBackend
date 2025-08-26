package com.financeiro.backend.domains.dtos;

import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaDTO {

    private Long id;
    private String descricao;
    private BigDecimal saldo;
    private TipoConta tipoConta;
    private String agencia;
    private String numero;
    private BigDecimal limite;

    private Long usuarioId;
    private String usuarioNome;
    private Long bancoId;
    private String bancoNome;

    public ContaDTO() { }

    public ContaDTO(Conta conta) {
        this.id = conta.getId();
        this.descricao = conta.getDescricao();
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
        this.agencia = conta.getAgencia();
        this.numero = conta.getNumero();
        this.limite = conta.getLimite();

        if (conta.getUsuario() != null) {
            this.usuarioId = conta.getUsuario().getId();
            this.usuarioNome = conta.getUsuario().getRazaoSocial();
        }

        if (conta.getBanco() != null) {
            this.bancoId = conta.getBanco().getId();
            this.bancoNome = conta.getBanco().getRazaoSocial();
        }
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public TipoConta getTipoConta() { return tipoConta; }
    public void setTipoConta(TipoConta tipoConta) { this.tipoConta = tipoConta; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public BigDecimal getLimite() { return limite; }
    public void setLimite(BigDecimal limite) { this.limite = limite; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public Long getBancoId() { return bancoId; }
    public void setBancoId(Long bancoId) { this.bancoId = bancoId; }

    public String getBancoNome() { return bancoNome; }
    public void setBancoNome(String bancoNome) { this.bancoNome = bancoNome; }
}
