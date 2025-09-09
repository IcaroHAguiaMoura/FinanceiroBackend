package com.financeiro.backend.services;

import com.financeiro.backend.domains.CentroCusto;
import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.Lancamento;
import com.financeiro.backend.domains.dtos.LancamentoDTO;
import com.financeiro.backend.domains.enums.Situacao;
import com.financeiro.backend.domains.enums.TipoConta;
import com.financeiro.backend.domains.enums.TipoLancamento;
import com.financeiro.backend.repositories.CentroCustoRepository;
import com.financeiro.backend.repositories.ContaRepository;
import com.financeiro.backend.repositories.LancamentoRepository;
import com.financeiro.backend.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    public List<LancamentoDTO> findAll() {
        return lancamentoRepository.findAll()
                .stream()
                .map(LancamentoDTO::new)
                .collect(Collectors.toList());
    }

    public Lancamento findById(Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado! ID: " + id));
    }

    public Lancamento create(LancamentoDTO dto) {
        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! ID: " + dto.getContaId()));

        CentroCusto centro = null;
        if (dto.getCentroCustoId() != null) {
            centro = centroCustoRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado! ID: " + dto.getCentroCustoId()));
        }

        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setParcela(dto.getParcela());
        lancamento.setDataLancamento(dto.getDataLancamento());
        lancamento.setDataVencimento(dto.getDataVencimento());
        lancamento.setDataBaixa(dto.getDataBaixa());
        lancamento.setTipoLancamento(dto.getTipoLancamento());
        lancamento.setValor(dto.getValor());
        lancamento.setSituacao(Situacao.ABERTO);
        lancamento.setConta(conta);
        lancamento.setCentroCusto(centro);

        return lancamentoRepository.save(lancamento);
    }

    public Lancamento update(Long id, LancamentoDTO dto) {
        Lancamento lancamento = findById(id);

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! ID: " + dto.getContaId()));

        CentroCusto centro = null;
        if (dto.getCentroCustoId() != null) {
            centro = centroCustoRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado! ID: " + dto.getCentroCustoId()));
        }

        lancamento.setDescricao(dto.getDescricao());
        lancamento.setParcela(dto.getParcela());
        lancamento.setDataLancamento(dto.getDataLancamento());
        lancamento.setDataVencimento(dto.getDataVencimento());
        lancamento.setDataBaixa(dto.getDataBaixa());
        lancamento.setTipoLancamento(dto.getTipoLancamento());
        lancamento.setValor(dto.getValor());
        lancamento.setConta(conta);
        lancamento.setCentroCusto(centro);

        return lancamentoRepository.save(lancamento);
    }

    public void delete(Long id) {
        Lancamento lancamento = findById(id);
        lancamentoRepository.delete(lancamento);
    }

    public Lancamento pagarLancamento(Long id) {
        Lancamento lancamento = findById(id);

        if (lancamento.getSituacao() == Situacao.ABERTO) {
            Conta conta = lancamento.getConta();
            BigDecimal saldo = conta.getSaldo() == null ? BigDecimal.ZERO : conta.getSaldo();
            BigDecimal limite = conta.getLimite() == null ? BigDecimal.ZERO : conta.getLimite();
            BigDecimal valor = lancamento.getValor();

            boolean isParcelado = false;
            int parcelaAtual = 1;
            int totalParcelas = 1;

            if (lancamento.getParcela() != null && lancamento.getParcela().contains("/")) {
                String[] partes = lancamento.getParcela().split("/");
                try {
                    parcelaAtual = Integer.parseInt(partes[0].trim());
                    totalParcelas = Integer.parseInt(partes[1].trim());
                    if (totalParcelas > 1) {
                        isParcelado = true;
                    }
                } catch (NumberFormatException ignored) {}
            }

            if (lancamento.getTipoLancamento() == TipoLancamento.DEBITO) {
                if (isParcelado) {
                    // desconta direto do limite
                    if (valor.compareTo(limite) > 0) {
                        throw new IllegalArgumentException("Limite insuficiente para realizar o débito parcelado!");
                    }
                    conta.setLimite(limite.subtract(valor));

                    // avança parcela
                    if (parcelaAtual < totalParcelas) {
                        parcelaAtual++;
                        lancamento.setParcela(parcelaAtual + "/" + totalParcelas);
                        lancamento.setSituacao(Situacao.ABERTO); // ainda em aberto
                    } else {
                        lancamento.setSituacao(Situacao.BAIXADO); // última parcela
                        lancamento.setDataBaixa(LocalDate.now());
                    }

                } else {
                    // caso normal: saldo e limite
                    BigDecimal totalDisponivel = saldo.add(limite);
                    if (valor.compareTo(totalDisponivel) > 0) {
                        throw new IllegalArgumentException("Saldo e limite insuficientes para realizar o débito!");
                    }

                    if (valor.compareTo(saldo) <= 0) {
                        conta.setSaldo(saldo.subtract(valor));
                    } else {
                        BigDecimal restante = valor.subtract(saldo);
                        conta.setSaldo(BigDecimal.ZERO);
                        conta.setLimite(limite.subtract(restante));
                    }

                    lancamento.setSituacao(Situacao.BAIXADO);
                    lancamento.setDataBaixa(LocalDate.now());
                }

            } else if (lancamento.getTipoLancamento() == TipoLancamento.CREDITO) {
                conta.setSaldo(conta.getSaldo().add(lancamento.getValor()));
                lancamento.setSituacao(Situacao.BAIXADO);
                lancamento.setDataBaixa(LocalDate.now());
            }

            contaRepository.save(conta);
            return lancamentoRepository.save(lancamento);
        }
        return lancamento;
    }

}
