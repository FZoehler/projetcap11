package br.com.fiap.exception;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}