package com.rf.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rf.relatorio.dto.LoginDTO;
import com.rf.relatorio.security.JwtUtil;
import com.rf.relatorio.dto.AuthResponseDTO;


@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public AuthResponseDTO login(@RequestBody LoginDTO dto){

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getUsername(),
                                dto.getPassword()
                        )
                );

                String token = jwtUtil.generateToken(dto.getUsername());
                return new AuthResponseDTO(token);
        }

        @GetMapping("/teste-senha")
        public String testarSenha(){

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                boolean resultado = encoder.matches(
                        "123456",
                        "$2a$10$Dow1hF9rK0X1dP4Zk1v8uO0gE6sT9wYkR9q1cJfZq3z2E5h5L3p1G"
                );

                return "Senha válida: " + resultado;
        }

        @GetMapping("/gerar")
        public String gerarSenha(){

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                return encoder.encode("123456");
        }

}
