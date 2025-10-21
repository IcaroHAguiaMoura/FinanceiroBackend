package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.Extrato;
import com.financeiro.backend.domains.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {
    List<Extrato> findByContaOrderByDataDesc(Conta conta);
}

