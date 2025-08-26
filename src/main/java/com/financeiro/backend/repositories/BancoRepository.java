package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository  extends JpaRepository <Banco,Long> {

    Optional<Banco> findByRazaoSocial(String razaoSocial);
}
