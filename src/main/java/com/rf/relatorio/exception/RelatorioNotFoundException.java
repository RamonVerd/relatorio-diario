package com.rf.relatorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RelatorioNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RelatorioNotFoundException(Long id) {
        super("Relatorio não existe com Id: " + id);
    }
	
}
