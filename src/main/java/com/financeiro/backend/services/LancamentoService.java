package com.financeiro.backend.services;

import com.financeiro.backend.domains.CentroCusto;
import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.Lancamento;
import com.financeiro.backend.domains.dtos.LancamentoDTO;
import com.financeiro.backend.domains.enums.Situacao;
import com.financeiro.backend.domains.enums.TipoLancamento;
import com.financeiro.backend.repositories.CentroCustoRepository;
import com.financeiro.backend.repositories.ContaRepository;
import com.financeiro.backend.repositories.LancamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado! ID: " + id));
    }

    public Lancamento create(LancamentoDTO dto) {
        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada! ID: " + dto.getContaId()));

        CentroCusto centro = null;
        if (dto.getCentroCustoId() != null) {
            centro = centroCustoRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new EntityNotFoundException("Centro de Custo não encontrado! ID: " + dto.getCentroCustoId()));
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
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada! ID: " + dto.getContaId()));

        CentroCusto centro = null;
        if (dto.getCentroCustoId() != null) {
            centro = centroCustoRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new EntityNotFoundException("Centro de Custo não encontrado! ID: " + dto.getCentroCustoId()));
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
        if(lancamento.getSituacao() == Situacao.ABERTO) {
            lancamento.setSituacao(Situacao.BAIXADO);
            lancamento.setDataBaixa(LocalDate.now());


            Conta conta = lancamento.getConta();
            if(lancamento.getTipoLancamento() == TipoLancamento.DEBITO) {
                conta.setSaldo(conta.getSaldo().subtract(lancamento.getValor()));
            } else if(lancamento.getTipoLancamento() == TipoLancamento.CREDITO) {
                conta.setSaldo(conta.getSaldo().add(lancamento.getValor()));
            }
            contaRepository.save(conta);
            return lancamentoRepository.save(lancamento);
        }
        return lancamento;
    }

}
