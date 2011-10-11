package stockwatch.exceptions;

public class SecuritiesParsingException extends Exception {
    private static final long serialVersionUID = -3919684876598564112L;
    private Exception hiddenException;
    
    public SecuritiesParsingException() {}
    
    public SecuritiesParsingException(String error, Exception excp) {
        super(error);
        hiddenException = excp;
    }
    
    public Exception getHiddenException() {
        return (hiddenException);
    }
}