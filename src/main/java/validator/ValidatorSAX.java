package validator;

import exception.ParserException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {

    public boolean valid(String xmlPath, String schemaXSD) throws ParserException {
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String logPath = "logs/log.txt";

        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            schema = factory.newSchema(new File(schemaXSD));
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setSchema(schema);
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(xmlPath, new PlanErrorHandler(logPath));
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e.getMessage() ,e );
        }
        File file = new File(logPath);
        return (file.length()==0);
    }
}