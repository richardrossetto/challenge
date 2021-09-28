package com.challenge.domain.exception;

public class VaccinationCenterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VaccinationCenterException(String mensagem) {
        super(mensagem);
    }

    public VaccinationCenterException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
