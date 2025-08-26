package com.financeiro.backend.domains.dtos;

import com.financeiro.backend.domains.Lancamento;
import com.financeiro.backend.domains.enums.Situacao;
import com.financeiro.backend.domains.enums.TipoLancamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDTO {

    private Long id;

    @NotNull(message = "Descrição é obrigatória")
    private String descricao;

    private String parcela;

    private LocalDate dataLancamento;
    private LocalDate dataVencimento;
    private LocalDate dataBaixa;

    @NotNull(message = "Tipo de lançamento é obrigatório")
    private TipoLancamento tipoLancamento;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private Situacao situacao;

    @NotNull(message = "Conta é obrigatória")
    private Long contaId;

    private Long centroCustoId;

    public LancamentoDTO() { }

    public LancamentoDTO(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.descricao = lancamento.getDescricao();
        this.parcela = lancamento.getParcela();
        this.dataLancamento = lancamento.getDataLancamento();
        this.dataVencimento = lancamento.getDataVencimento();
        this.dataBaixa = lancamento.getDataBaixa();
        this.tipoLancamento = lancamento.getTipoLancamento();
        this.valor = lancamento.getValor();
        this.situacao = lancamento.getSituacao();
        this.contaId = lancamento.getConta() != null ? lancamento.getConta().getId() : null;
        this.centroCustoId = lancamento.getCentroCusto() != null ? lancamento.getCentroCusto().getId() : null;
    }

    // Getters e setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getParcela() { return parcela; }
    public void setParcela(String parcela) { this.parcela = parcela; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public LocalDate getDataBaixa() { return dataBaixa; }
    public void setDataBaixa(LocalDate dataBaixa) { this.dataBaixa = dataBaixa; }

    public TipoLancamento getTipoLancamento() { return tipoLancamento; }
    public void setTipoLancamento(TipoLancamento tipoLancamento) { this.tipoLancamento = tipoLancamento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Situacao getSituacao() { return situacao; }
    public void setSituacao(Situacao situacao) { this.situacao = situacao; }

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }

    public Long getCentroCustoId() { return centroCustoId; }
    public void setCentroCustoId(Long centroCustoId) { this.centroCustoId = centroCustoId; }
}
