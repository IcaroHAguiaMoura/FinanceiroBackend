package com.financeiro.backend.services;

import com.financeiro.backend.domains.Pessoa;
import com.financeiro.backend.domains.dtos.PessoaDTO;
import com.financeiro.backend.repositories.PessoaRepository;
import com.financeiro.backend.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepo;


    public List<PessoaDTO> findAll() {
        return pessoaRepo.findAll().stream()
                .map(PessoaDTO::new)
                .collect(Collectors.toList());
    }


    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = pessoaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! ID: " + id));
    }


    public Pessoa create(PessoaDTO dto) {
        dto.setId(null);
        validaEmail(dto);
        Pessoa obj = new Pessoa(dto.getRazaoSocial(), dto.getEmail(), dto.getSenha());
        return pessoaRepo.save(obj);
    }

    public Pessoa update(Long id, PessoaDTO dto) {
        dto.setId(id);
        Pessoa oldObj = findById(id);
        oldObj.setRazaoSocial(dto.getRazaoSocial());
        oldObj.setEmail(dto.getEmail());
        oldObj.setSenha(dto.getSenha());
        validaEmail(dto, oldObj.getId());
        return pessoaRepo.save(oldObj);
    }


    public void delete(Long id) {
        Pessoa obj = findById(id);
        pessoaRepo.deleteById(id);
    }


    private void validaEmail(PessoaDTO dto) {
        Optional<Pessoa> obj = pessoaRepo.findByEmail(dto.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }
    }

    private void validaEmail(PessoaDTO dto, Long idAtual) {
        Optional<Pessoa> obj = pessoaRepo.findByEmail(dto.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(idAtual)) {
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }
    }
}
