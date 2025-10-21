package com.financeiro.backend.services;

import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.Extrato;
import com.financeiro.backend.domains.Lancamento;
import com.financeiro.backend.domains.dtos.ExtratoDTO;
import com.financeiro.backend.domains.enums.TipoLancamento;
import com.financeiro.backend.repositories.ContaRepository;
import com.financeiro.backend.repositories.ExtratoRepository;
import com.financeiro.backend.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtratoService {

    @Autowired
    private ExtratoRepository extratoRepo;

    @Autowired
    private ContaRepository contaRepo;

    public List<ExtratoDTO> findAll() {
        return extratoRepo.findAll().stream()
                .map(ExtratoDTO::new)
                .collect(Collectors.toList());
    }

    public Extrato findById(Long id) {
        Optional<Extrato> obj = extratoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Extrato não encontrado! ID: " + id));
    }

    @Transactional
    public Extrato create(ExtratoDTO dto) {
        Conta conta = contaRepo.findById(dto.getContaId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! ID: " + dto.getContaId()));

        if (dto.getValor() == null || dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do lançamento deve ser maior que zero.");
        }

        Extrato extrato = new Extrato();
        extrato.setDescricao(dto.getDescricao());
        extrato.setValor(dto.getValor());
        extrato.setTipoLancamento(dto.getTipoLancamento());
        extrato.setData(LocalDateTime.now());
        extrato.setConta(conta);

        BigDecimal saldoAtual = conta.getSaldo() != null ? conta.getSaldo() : BigDecimal.ZERO;

        if (dto.getTipoLancamento() == TipoLancamento.CREDITO) {
            saldoAtual = saldoAtual.add(dto.getValor());
        } else {
            saldoAtual = saldoAtual.subtract(dto.getValor());
        }

        conta.setSaldo(saldoAtual);
        extrato.setSaldoAposMovimentacao(saldoAtual);

        contaRepo.save(conta);
        return extratoRepo.save(extrato);
    }

    public void delete(Long id) {
        Extrato extrato = findById(id);
        extratoRepo.delete(extrato);
    }

    public void registrarMovimentacaoAutomaticamente(Lancamento lancamento) {
        Conta conta = lancamento.getConta();
        if (conta == null) {
            throw new IllegalArgumentException("O lançamento não está vinculado a nenhuma conta!");
        }

        Extrato extrato = new Extrato();
        extrato.setConta(conta);
        extrato.setDescricao("Movimentação automática: " + lancamento.getDescricao());
        extrato.setValor(lancamento.getValor());
        extrato.setTipoLancamento(lancamento.getTipoLancamento());
        extrato.setData(LocalDateTime.now());
        extrato.setSaldoAposMovimentacao(conta.getSaldo());
        extrato.setLimiteAposMovimentacao(conta.getLimite());

        extratoRepo.save(extrato);
    }

}

