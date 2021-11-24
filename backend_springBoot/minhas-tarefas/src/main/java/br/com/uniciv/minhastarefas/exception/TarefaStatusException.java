package br.com.uniciv.minhastarefas.exception;

import lombok.Data;

@Data
public class TarefaStatusException extends RuntimeException {

    public TarefaStatusException() {

        super();
    }

    public TarefaStatusException(String message, Throwable cause, boolean enableSupression,
                                 boolean writableStackTrace) {

        super(message, cause, enableSupression, writableStackTrace);
    }

    public TarefaStatusException(String message, Throwable cause) {

        super(message, cause);
    }

    public TarefaStatusException(String message) {

        super(message);
    }

    public TarefaStatusException(Throwable cause) {

        super(cause);
    }
}
