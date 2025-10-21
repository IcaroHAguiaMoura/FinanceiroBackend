package com.financeiro.backend.domains.dtos;

import com.financeiro.backend.domains.Extrato;
import com.financeiro.backend.domains.enums.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExtratoDTO {

    private Long id;
    private LocalDateTime data;
    private String descricao;
    private BigDecimal valor;
    private TipoLancamento tipoLancamento;
    private BigDecimal saldoAposMovimentacao;
    private BigDecimal limiteAposMovimentacao;
    private Long contaId;

    public ExtratoDTO() {}

    public ExtratoDTO(Extrato obj) {
        this.id = obj.getId();
        this.data = obj.getData();
        this.descricao = obj.getDescricao();
        this.valor = obj.getValor();
        this.tipoLancamento = obj.getTipoLancamento();
        this.saldoAposMovimentacao = obj.getSaldoAposMovimentacao();
        this.limiteAposMovimentacao = obj.getLimiteAposMovimentacao();
        this.contaId = obj.getConta().getId();
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

    public BigDecimal getLimiteAposMovimentacao() {
        return limiteAposMovimentacao;
    }
    public void setLimiteAposMovimentacao(BigDecimal limiteAposMovimentacao) {
        this.limiteAposMovimentacao = limiteAposMovimentacao;
    }

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }
}

