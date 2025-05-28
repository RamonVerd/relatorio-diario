package com.rf.relatorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EscalaServicoNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public EscalaServicoNotFoundException(Long id) {
        super("Escala não existe com Id: " + id);
    }

}
