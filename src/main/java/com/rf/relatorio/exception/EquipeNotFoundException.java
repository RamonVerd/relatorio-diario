package com.rf.relatorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EquipeNotFoundException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public EquipeNotFoundException(Long id) {
        super("Equipe não existe com Id: " + id);
    }
}
