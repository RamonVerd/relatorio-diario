package com.rf.relatorio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rf.relatorio.entity.Usuario;
import com.rf.relatorio.repository.UsuarioRepository;

@Service
public class UsuarioService  implements UserDetailsService {

        @Autowired
        private UsuarioRepository repository;

        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {

                Usuario usuario = repository.findByUsername(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("Usuário não encontrado"));
                                System.out.println("ROLE DO BANCO: " + usuario.getRole());
                                return org.springframework.security.core.userdetails.User
                                        .builder()
                                        .username(usuario.getUsername())
                                        .password(usuario.getPassword())
                                        .authorities(
                                        new SimpleGrantedAuthority("ROLE_" + usuario.getRole())
                                        )
                                        .build();
        }


}
