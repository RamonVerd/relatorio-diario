package com.rf.relatorio.service;

import org.springframework.beans.factory.annotation.Autowired;
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
                                return org.springframework.security.core.userdetails.User
                                        .builder()
                                        .username(usuario.getUsername())
                                        .password(usuario.getPassword())
                                        .roles(usuario.getRole())
                                        .build();
        }

}
