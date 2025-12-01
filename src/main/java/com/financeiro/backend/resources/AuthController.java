package com.financeiro.backend.resources;

import com.financeiro.backend.domains.Usuario;
import com.financeiro.backend.domains.dtos.CredenciaisDTO;
import com.financeiro.backend.domains.dtos.TokenDTO;
import com.financeiro.backend.domains.dtos.UsuarioDTO;
import com.financeiro.backend.domains.enums.TipoPessoa;
import com.financeiro.backend.repositories.UsuarioRepository;
import com.financeiro.backend.security.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JWTUtils jwtUtils,
                          UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredenciaisDTO credenciais) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credenciais.getUsername(),
                            credenciais.getPassword()
                    )
            );

            String token = jwtUtils.generateToken(credenciais.getUsername());
            return ResponseEntity.ok(new TokenDTO(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    // ================= CADASTRO =================
    @PostMapping("/register")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            // Verifica se email já existe
            if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email já cadastrado");
            }

            // Cria usuário
            Usuario usuario = new Usuario(
                    null, // id será gerado
                    usuarioDTO.getRazaoSocial(),
                    usuarioDTO.getEmail(),
                    passwordEncoder.encode(usuarioDTO.getSenha()), // senha criptografada
                    usuarioDTO.getTipoPessoa() != null ? usuarioDTO.getTipoPessoa() : TipoPessoa.CLIENTE
            );

            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
