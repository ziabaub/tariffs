package exception;

public class ParserException extends Exception {

    public ParserException() {
        super("ParserException : Can't continue Parsing check xml file  \n");
    }

    public ParserException(String message, Exception e) {
        super(message, e);
    }

    public ParserException(String message) {
        super("ParserException : Can't continue Parsing : " + message);
    }

    public ParserException(Exception e) {
        super(e);
    }

}
