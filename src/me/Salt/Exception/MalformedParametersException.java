package me.Salt.Exception;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class MalformedParametersException extends Exception {
    public MalformedParametersException() {
        super();
    }

    public MalformedParametersException(String message) {
        super(message);
    }

    public MalformedParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedParametersException(Throwable cause) {
        super(cause);
    }
}
