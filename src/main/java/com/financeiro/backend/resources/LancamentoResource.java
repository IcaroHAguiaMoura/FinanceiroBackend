package com.financeiro.backend.resources;

import com.financeiro.backend.domains.Lancamento;
import com.financeiro.backend.domains.dtos.LancamentoDTO;
import com.financeiro.backend.services.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> findAll() {
        return ResponseEntity.ok(lancamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> findById(@PathVariable Long id) {
        Lancamento lancamento = lancamentoService.findById(id);
        return ResponseEntity.ok(new LancamentoDTO(lancamento));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody LancamentoDTO dto) {
        Lancamento lancamento = lancamentoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lancamento.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> update(@PathVariable Long id, @Valid @RequestBody LancamentoDTO dto) {
        Lancamento lancamento = lancamentoService.update(id, dto);
        return ResponseEntity.ok(new LancamentoDTO(lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lancamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/pagar/{id}")
    public ResponseEntity<LancamentoDTO> pagar(@PathVariable Long id) {
        Lancamento lancamento = lancamentoService.pagarLancamento(id);
        return ResponseEntity.ok(new LancamentoDTO(lancamento));
    }

}
