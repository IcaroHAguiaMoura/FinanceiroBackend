package com.financeiro.backend.services;

import com.financeiro.backend.domains.Banco;
import com.financeiro.backend.domains.Conta;
import com.financeiro.backend.domains.Usuario;
import com.financeiro.backend.domains.dtos.ContaDTO;
import com.financeiro.backend.repositories.BancoRepository;
import com.financeiro.backend.repositories.ContaRepository;
import com.financeiro.backend.repositories.UsuarioRepository;
import com.financeiro.backend.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BancoRepository bancoRepo;

    public List<ContaDTO> findAll() {
        return contaRepo.findAll().stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    public Conta findById(Long id) {
        Optional<Conta> obj = contaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + id));
    }

    public Conta create(ContaDTO dto) {
        dto.setId(null);
        validaConta(dto);
        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + dto.getUsuarioId()));

        Banco banco = bancoRepo.findById(dto.getBancoId())
                .orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: " + dto.getBancoId()));

        Conta conta = new Conta();
        conta.setDescricao(dto.getDescricao());
        conta.setSaldo(dto.getSaldo());
        conta.setTipoConta(dto.getTipoConta());
        conta.setAgencia(dto.getAgencia());
        conta.setNumero(dto.getNumero());
        conta.setLimite(dto.getLimite());
        conta.setUsuario(usuario);
        conta.setBanco(banco);

        return contaRepo.save(conta);
    }

    public Conta update(Long id, ContaDTO dto) {
        Conta oldConta = findById(id);
        dto.setId(id);
        validaConta(dto);

        oldConta.setDescricao(dto.getDescricao());
        oldConta.setSaldo(dto.getSaldo());
        oldConta.setTipoConta(dto.getTipoConta());
        oldConta.setAgencia(dto.getAgencia());
        oldConta.setNumero(dto.getNumero());
        oldConta.setLimite(dto.getLimite());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + dto.getUsuarioId()));
            oldConta.setUsuario(usuario);
        }

        if (dto.getBancoId() != null) {
            Banco banco = bancoRepo.findById(dto.getBancoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: " + dto.getBancoId()));
            oldConta.setBanco(banco);
        }

        return contaRepo.save(oldConta);
    }

    public void delete(Long id) {
        Conta conta = findById(id);
        contaRepo.delete(conta);
    }

    private void validaConta(ContaDTO dto) {
        Optional<Conta> obj = contaRepo.findByNumero(dto.getNumero());
        if (obj.isPresent() && !obj.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Número da conta já cadastrado!");
        }
    }
}
