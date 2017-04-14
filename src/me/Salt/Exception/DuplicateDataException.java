package me.Salt.Exception;

/**
 * Created by Salt001 on 10/04/2017.
 */
public class DuplicateDataException extends Exception {
    public DuplicateDataException() {
        super();
    }

    public DuplicateDataException(String message) {
        super(message);
    }

    public DuplicateDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateDataException(Throwable cause) {
        super(cause);
    }
}
