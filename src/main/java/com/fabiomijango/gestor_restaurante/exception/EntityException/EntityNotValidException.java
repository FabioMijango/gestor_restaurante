package com.fabiomijango.gestor_restaurante.exception.EntityException;

public class EntityNotValidException extends RuntimeException {
    public EntityNotValidException(String message) {
        super(message);
    }

    public  EntityNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
