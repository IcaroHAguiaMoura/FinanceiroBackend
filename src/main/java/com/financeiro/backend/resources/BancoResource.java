package com.financeiro.backend.resources;

import com.financeiro.backend.domains.Banco;
import com.financeiro.backend.domains.dtos.BancoDTO;
import com.financeiro.backend.services.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/banco")
public class BancoResource {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public ResponseEntity<List<BancoDTO>> findAll() {
        return ResponseEntity.ok().body(bancoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> findById(@PathVariable Long id) {
        Banco banco = bancoService.findById(id);
        return ResponseEntity.ok().body(new BancoDTO(banco));
    }

    @PostMapping
    public ResponseEntity<BancoDTO> create(@Valid @RequestBody BancoDTO dto) {
        Banco banco = bancoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(banco.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDTO> update(@PathVariable Long id, @Valid @RequestBody BancoDTO dto) {
        Banco banco = bancoService.update(id, dto);
        return ResponseEntity.ok().body(new BancoDTO(banco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bancoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
