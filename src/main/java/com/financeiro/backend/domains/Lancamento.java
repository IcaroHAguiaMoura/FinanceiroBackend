package com.financeiro.backend.domains;

import com.financeiro.backend.domains.enums.Situacao;
import com.financeiro.backend.domains.enums.TipoLancamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Lancamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String parcela; // 1/3 (teste)

    private LocalDate dataLancamento;
    private LocalDate dataVencimento;
    private LocalDate dataBaixa; // preencher ao dar baixa (teste)


    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento; //Credito é o saldo, Debito é pagamento

    @Column(precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Situacao situacao = Situacao.ABERTO;

    @ManyToOne(optional = false)
    private Conta conta;

    @ManyToOne
    private CentroCusto centroCusto;

    public Lancamento() {
    }

    public Lancamento(Long id, String descricao, String parcela, LocalDate dataLancamento, LocalDate dataVencimento, LocalDate dataBaixa, TipoLancamento tipoLancamento, BigDecimal valor, Situacao situacao, Conta conta, CentroCusto centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.parcela = parcela;
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        this.situacao = situacao;
        this.conta = conta;
        this.centroCusto = centroCusto;
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

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }
}
