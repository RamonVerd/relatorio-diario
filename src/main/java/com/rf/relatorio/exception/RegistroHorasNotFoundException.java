package com.rf.relatorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RegistroHorasNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public RegistroHorasNotFoundException(Long id) {
        super("registro de horas não existe com Id: " + id);
    }

}
