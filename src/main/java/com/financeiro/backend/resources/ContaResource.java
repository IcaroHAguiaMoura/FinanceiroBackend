package com.financeiro.backend.resources;

import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.dtos.ContaDTO;
import com.financeiro.backend.services.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        return ResponseEntity.ok().body(contaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Long id) {
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok().body(new ContaDTO(conta));
    }

    @PostMapping
    public ResponseEntity<ContaDTO> create(@Valid @RequestBody ContaDTO dto) {
        Conta conta = contaService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @Valid @RequestBody ContaDTO dto) {
        Conta conta = contaService.update(id, dto);
        return ResponseEntity.ok().body(new ContaDTO(conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
