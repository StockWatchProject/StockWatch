package stockwatch.exceptions;

public class SecuritiesParsingException extends Exception {
    private Exception hiddenException;
    
    public SecuritiesParsingException(String error, Exception excp) {
        super(error);
        hiddenException = excp;
    }
    
    public Exception getHiddenException() {
        return (hiddenException);
    }
}