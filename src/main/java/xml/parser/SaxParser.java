package xml.parser;

import entity.Plan;
import exception.ParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements XmlParser {
    private PlanHandler planHandler;

    public SaxParser(PlanHandler planHandler) {
        this.planHandler = planHandler;
    }

    @Override
    public List<Plan> parse(String path) throws ParserException {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(planHandler);
            reader.parse(path);
            return planHandler.getPlans();
        } catch (SAXException | IOException e) {
            throw new ParserException(e.getMessage() , e);
        }

    }
}
