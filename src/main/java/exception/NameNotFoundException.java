package exception;

public class NameNotFoundException extends Exception {
    public NameNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

    public NameNotFoundException(String message){
        super(message);
    }
}
