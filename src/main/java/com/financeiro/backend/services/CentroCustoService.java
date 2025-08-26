package com.financeiro.backend.services;

import com.financeiro.backend.domains.CentroCusto;
import com.financeiro.backend.domains.dtos.CentroCustoDTO;
import com.financeiro.backend.repositories.CentroCustoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CentroCustoService {

    @Autowired
    private CentroCustoRepository centroCustoRepo;


    public List<CentroCustoDTO> findAll() {
        return centroCustoRepo.findAll()
                .stream()
                .map(CentroCustoDTO::new)
                .collect(Collectors.toList());
    }


    public CentroCusto findById(Long id) {
        Optional<CentroCusto> obj = centroCustoRepo.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Centro de Custo não encontrado! ID: " + id));
    }


    public CentroCusto create(CentroCustoDTO dto) {
        dto.setId(null);
        validaCentroCusto(dto);
        CentroCusto obj = new CentroCusto();
        obj.setRazaoSocial(dto.getRazaoSocial());
        return centroCustoRepo.save(obj);
    }


    public CentroCusto update(Long id, CentroCustoDTO dto) {
        dto.setId(id);
        CentroCusto oldObj = findById(id);
        oldObj.setRazaoSocial(dto.getRazaoSocial());
        return centroCustoRepo.save(oldObj);
    }


    public void delete(Long id) {
        CentroCusto obj = findById(id);
        centroCustoRepo.delete(obj);
    }


    private void validaCentroCusto(CentroCustoDTO dto) {
        Optional<CentroCusto> obj = centroCustoRepo.findByRazaoSocial(dto.getRazaoSocial());
        if (obj.isPresent() && !obj.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Centro de Custo já cadastrado!");
        }
    }
}
