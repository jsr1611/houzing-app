package uz.digitalone.houzingapp.Exception;

public class VerifyTokenNotFound extends RuntimeException {
    public VerifyTokenNotFound(String message) {
        super(message);
    }

    public VerifyTokenNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
