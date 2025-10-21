package com.financeiro.backend.domains;

import com.financeiro.backend.domains.enums.TipoLancamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "extratos")
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private String descricao;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento; // ENTRADA ou SAIDA


    @Column(precision = 19, scale = 2)
    private BigDecimal saldoAposMovimentacao;

    @Column(precision = 19, scale = 2)
    private BigDecimal limiteAposMovimentacao;

    @ManyToOne(optional = false)
    private Conta conta;

    public Extrato() {}

    public Extrato(Long id, LocalDateTime data, String descricao, BigDecimal valor,
                   TipoLancamento tipoLancamento, BigDecimal saldoAposMovimentacao, BigDecimal limiteAposMovimentacao, Conta conta) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
        this.saldoAposMovimentacao = saldoAposMovimentacao;
        this.limiteAposMovimentacao = limiteAposMovimentacao;
        this.conta = conta;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public TipoLancamento getTipoLancamento() { return tipoLancamento; }
    public void setTipoLancamento(TipoLancamento tipoLancamento) { this.tipoLancamento = tipoLancamento; }

    public BigDecimal getSaldoAposMovimentacao() { return saldoAposMovimentacao; }
    public void setSaldoAposMovimentacao(BigDecimal saldoAposMovimentacao) { this.saldoAposMovimentacao = saldoAposMovimentacao; }

    public BigDecimal getLimiteAposMovimentacao() { return limiteAposMovimentacao; }
    public void setLimiteAposMovimentacao(BigDecimal limiteAposMovimentacao) { this.limiteAposMovimentacao = limiteAposMovimentacao; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
}