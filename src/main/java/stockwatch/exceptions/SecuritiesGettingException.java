package stockwatch.exceptions;

public class SecuritiesGettingException extends Exception {
    private Exception hiddenException;
    
    public SecuritiesGettingException(String error, Exception excp) {
        super(error);
        hiddenException = excp;
    }
    
    public Exception getHiddenException() {
        return (hiddenException);
    }
}