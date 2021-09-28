package com.challenge.domain.exception;

public class PersonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonException(String mensagem) {
        super(mensagem);
    }

    public PersonException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
