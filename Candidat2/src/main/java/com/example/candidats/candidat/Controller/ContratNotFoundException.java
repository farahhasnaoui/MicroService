package com.example.candidats.candidat.Controller;

public class ContratNotFoundException extends RuntimeException {

    public ContratNotFoundException() {
        super();
    }

    public ContratNotFoundException(String message) {
        super(message);
    }

    public ContratNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}