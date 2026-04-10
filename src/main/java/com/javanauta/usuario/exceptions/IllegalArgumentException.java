package com.javanauta.usuario.exceptions;

public class IlegalException extends RuntimeException {

    public IllegalArgumentException(String message) {
        super(message);
    }
}
