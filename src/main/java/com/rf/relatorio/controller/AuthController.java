package com.rf.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.rf.relatorio.dto.LoginDTO;
import com.rf.relatorio.security.JwtService;
import com.rf.relatorio.dto.AuthResponseDTO;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

        @Autowired
        private JwtService jwtService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public AuthResponseDTO login(@RequestBody LoginDTO dto){

                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getUsername(),
                                dto.getPassword()
                        )
                );

                String token = jwtService.generateToken(auth); 
                return new AuthResponseDTO(token);
        }

        // @GetMapping("/teste-senha")
        // public String testarSenha(){

        //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //         boolean resultado = encoder.matches(
        //                 "24680",
        //                 "$2a$10$AipetkGvs/f9SVtDEDqtvu4OPtBAidd2x368LglhBucl7PpH3phsW"
        //         );

        //         return "Senha válida: " + resultado;
        // }

        // @GetMapping("/gerar")
        // public String gerarSenha(){

        //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //         return encoder.encode("24680");
        // }

}
