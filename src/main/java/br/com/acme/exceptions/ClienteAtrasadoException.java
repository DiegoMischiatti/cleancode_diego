package br.com.acme.exceptions;

import java.io.Serial;

public class ClienteAtrasadoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8332142176853224884L;

    public ClienteAtrasadoException(String message) {
        super(message);
    }
}
