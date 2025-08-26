package com.financeiro.backend.services;

import com.financeiro.backend.domains.*;
import com.financeiro.backend.domains.enums.Situacao;
import com.financeiro.backend.domains.enums.TipoConta;
import com.financeiro.backend.domains.enums.TipoLancamento;
import com.financeiro.backend.domains.enums.TipoPessoa;
import com.financeiro.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public void initDB() {


        Usuario usuario1 = new Usuario(null, "Teste Usuario", "teste@gmail.com", "123", TipoPessoa.CLIENTE);
        usuarioRepository.save(usuario1);


        Banco banco1 = new Banco(null, "Banco Central");
        bancoRepository.save(banco1);


        Conta conta1 = new Conta(null, "Conta Corrente Teste", BigDecimal.valueOf(1000), TipoConta.CONTA_CORRENTE, "1234", "56789-0", BigDecimal.valueOf(500), usuario1, banco1
        );
        contaRepository.save(conta1);


        CentroCusto centro1 = new CentroCusto(null, "Centro de Custo Teste");
        centroCustoRepository.save(centro1);


        Lancamento lancamento1 = new Lancamento(null, "Pagamento Energia", "1/1", LocalDate.now(), LocalDate.now().plusDays(10), null, TipoLancamento.DEBITO, BigDecimal.valueOf(150), Situacao.ABERTO, conta1, centro1);
        lancamentoRepository.save(lancamento1);
    }
}
