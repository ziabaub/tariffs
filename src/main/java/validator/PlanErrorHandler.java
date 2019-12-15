package validator;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class PlanErrorHandler extends DefaultHandler {
    private Logger logger = Logger.getLogger("handler");

    public PlanErrorHandler(String logName) throws IOException {
        logger.addAppender( new FileAppender(new SimpleLayout(), logName));
    }


    @Override
    public void warning(SAXParseException e) {
        logger.warn(getLineAddress(e) + "-" + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.error(getLineAddress(e) + "-" + e.getMessage());
    }

    private String getLineAddress(SAXParseException e ){
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
