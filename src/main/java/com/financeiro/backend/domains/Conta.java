package com.financeiro.backend.domains;

import com.financeiro.backend.domains.enums.TipoConta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(precision = 19, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    private String agencia;
    private String numero;

    @Column(precision = 19, scale = 2)
    private BigDecimal limite = BigDecimal.ZERO;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne
    private Banco banco;

    @OneToMany(mappedBy = "conta")
    private List<Lancamento> Lancamentos = new ArrayList<>();

    public Conta(){}

    public Conta(Long id, String descricao, BigDecimal saldo, TipoConta tipoConta, String agencia, String numero, BigDecimal limite, Usuario usuario, Banco banco) {
        this.id = id;
        this.descricao = descricao;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.numero = numero;
        this.limite = limite;
        this.usuario = usuario;
        this.banco = banco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
