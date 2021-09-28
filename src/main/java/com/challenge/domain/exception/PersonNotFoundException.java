package com.challenge.domain.exception;

public class PersonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PersonNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
