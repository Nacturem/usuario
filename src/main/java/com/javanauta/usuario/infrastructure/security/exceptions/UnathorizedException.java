package com.javanauta.usuario.infrastructure.security.exceptions;

public class UnathorizedException extends RuntimeException {

    public UnathorizedException(String mensagem){
        super(mensagem);
    }

    public UnathorizedException(String mensagem, Throwable throwable){
        super(mensagem,throwable);
    }





}
