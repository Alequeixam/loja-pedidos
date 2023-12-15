package com.alex.demo.lojapedidos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CPFAlreadyExistsException extends RuntimeException {
    public CPFAlreadyExistsException(String message) {
        super(message);
    }
}
