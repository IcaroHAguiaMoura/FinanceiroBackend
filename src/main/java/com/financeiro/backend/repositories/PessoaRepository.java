package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.Pessoa;
import com.financeiro.backend.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Optional<Pessoa> findByEmail(String email);

}
