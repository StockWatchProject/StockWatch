package stockwatch.exceptions;

public class SecuritiesGettingException extends Exception {
    private static final long serialVersionUID = -2357314933395296173L;
    private Exception hiddenException;
    
    public SecuritiesGettingException(String error, Exception excp) {
        super(error);
        hiddenException = excp;
    }
    
    public Exception getHiddenException() {
        return (hiddenException);
    }
}