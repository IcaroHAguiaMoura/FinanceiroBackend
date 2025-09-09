package com.financeiro.backend.services;

import com.financeiro.backend.domains.Banco;
import com.financeiro.backend.domains.dtos.BancoDTO;
import com.financeiro.backend.repositories.BancoRepository;
import com.financeiro.backend.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepo;


    public List<BancoDTO> findAll() {
        return bancoRepo.findAll()
                .stream()
                .map(BancoDTO::new)
                .collect(Collectors.toList());
    }


    public Banco findById(Long id) {
        Optional<Banco> obj = bancoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! ID: " + id));
    }


    public Banco create(BancoDTO dto) {
        dto.setId(null);
        validaBanco(dto.getId(),dto.getRazaoSocial());
        Banco obj = new Banco();
        obj.setRazaoSocial(dto.getRazaoSocial());
        return bancoRepo.save(obj);
    }


    public Banco update(Long id, BancoDTO dto) {
        dto.setId(id);
        Banco oldObj = findById(id);
        validaBanco(id, dto.getRazaoSocial());
        oldObj.setRazaoSocial(dto.getRazaoSocial());
        return bancoRepo.save(oldObj);
    }

    public void delete(Long id) {
        Banco obj = findById(id);
        bancoRepo.delete(obj);
    }


    private void validaBanco(Long id, String razaoSocial) {
        Optional<Banco> obj = bancoRepo.findByRazaoSocial(razaoSocial);
        if (obj.isPresent() && !obj.get().getId().equals(id)) {
            throw new DataIntegrityViolationException("Banco já cadastrado!");
        }
    }
}
