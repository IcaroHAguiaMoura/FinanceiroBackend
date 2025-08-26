package com.financeiro.backend.domains.dtos;

import com.financeiro.backend.domains.CentroCusto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CentroCustoDTO {

    private Long id;

    @NotNull(message = "O campo razão social é obrigatório")
    @NotBlank(message = "O campo razão social não pode estar vazio")
    private String razaoSocial;

    public CentroCustoDTO() { }

    public CentroCustoDTO(CentroCusto centroCusto) {
        this.id = centroCusto.getId();
        this.razaoSocial = centroCusto.getRazaoSocial();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
