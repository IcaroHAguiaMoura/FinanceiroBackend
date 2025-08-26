package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroCustoRepository extends JpaRepository <CentroCusto,Long> {
    Optional<CentroCusto> findByRazaoSocial (String razaoSocial);
}
