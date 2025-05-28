package com.rf.relatorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AgenteUserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AgenteUserNotFoundException(Long id) {
        super("Agente não existe com Id: " + id);
    }

}
