package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository <Lancamento,Long> {
}
