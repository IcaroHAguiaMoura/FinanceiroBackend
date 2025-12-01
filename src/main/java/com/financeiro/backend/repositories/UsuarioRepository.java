package com.financeiro.backend.repositories;

import com.financeiro.backend.domains.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(@Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String email);
}
