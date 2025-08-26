package com.financeiro.backend.resources;

import com.financeiro.backend.domains.CentroCusto;
import com.financeiro.backend.domains.dtos.CentroCustoDTO;
import com.financeiro.backend.services.CentroCustoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/centrocusto")
public class CentroCustoResource {

    @Autowired
    private CentroCustoService centroCustoService;

    @GetMapping
    public ResponseEntity<List<CentroCustoDTO>> findAll() {
        return ResponseEntity.ok().body(centroCustoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroCustoDTO> findById(@PathVariable Long id) {
        CentroCusto centro = centroCustoService.findById(id);
        return ResponseEntity.ok().body(new CentroCustoDTO(centro));
    }

    @PostMapping
    public ResponseEntity<CentroCustoDTO> create(@Valid @RequestBody CentroCustoDTO dto) {
        CentroCusto centro = centroCustoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(centro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroCustoDTO> update(@PathVariable Long id, @Valid @RequestBody CentroCustoDTO dto) {
        CentroCusto centro = centroCustoService.update(id, dto);
        return ResponseEntity.ok().body(new CentroCustoDTO(centro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        centroCustoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
