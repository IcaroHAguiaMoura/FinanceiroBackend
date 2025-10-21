package com.financeiro.backend.resources;
import com.financeiro.backend.domains.Extrato;
import com.financeiro.backend.domains.dtos.ExtratoDTO;
import com.financeiro.backend.services.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/extratos")
@CrossOrigin("*")
public class ExtratoResource {

    @Autowired
    private ExtratoService extratoService;

    @GetMapping
    public ResponseEntity<List<ExtratoDTO>> findAll() {
        return ResponseEntity.ok(extratoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Extrato> findById(@PathVariable Long id) {
        return ResponseEntity.ok(extratoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Extrato> create(@RequestBody ExtratoDTO dto) {
        Extrato novo = extratoService.create(dto);
        URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        extratoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

