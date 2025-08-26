package com.financeiro.backend.services;

import com.financeiro.backend.domains.Usuario;
import com.financeiro.backend.domains.dtos.UsuarioDTO;
import com.financeiro.backend.domains.enums.TipoPessoa;
import com.financeiro.backend.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<UsuarioDTO> findAll() {
        return usuarioRepo.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = usuarioRepo.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado! ID: " + id));
    }

    public Usuario create(UsuarioDTO dto) {
        dto.setId(null);
        validaEmail(dto.getEmail());

        Usuario usuario = new Usuario(
                dto.getId(),
                dto.getRazaoSocial(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getTipoPessoa()
        );

        return usuarioRepo.save(usuario);
    }

    public Usuario update(Long id, UsuarioDTO dto) {
        Usuario oldUsuario = findById(id);
        dto.setId(id);

        validaEmail(dto.getEmail());

        oldUsuario.setRazaoSocial(dto.getRazaoSocial());
        oldUsuario.setEmail(dto.getEmail());
        oldUsuario.setSenha(dto.getSenha());
        oldUsuario.setTipoPessoa(dto.getTipoPessoa());

        return usuarioRepo.save(oldUsuario);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepo.delete(usuario);
    }

    private void validaEmail(String email) {
        Optional<Usuario> obj = usuarioRepo.findByEmail(email);
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }
}
