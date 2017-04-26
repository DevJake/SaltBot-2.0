package me.Salt.Exception.Evaluation;

public class EvaluationReturnedNullException extends EvaluationException {
    public EvaluationReturnedNullException() {
        super();
    }

    public EvaluationReturnedNullException(String message) {
        super(message);
    }

    public EvaluationReturnedNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluationReturnedNullException(Throwable cause) {
        super(cause);
    }
}
