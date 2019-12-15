package xml.parser;

import entity.Plan;
import entity.Plans;
import exception.ParserException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JaxBParser implements XmlParser{

    public List<Plan> parse(String xmlPath) throws ParserException {
        JAXBContext jaxbContext;
        FileReader reader;

        try {
            jaxbContext = JAXBContext.newInstance(Plans.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reader = new FileReader(xmlPath);
            Plans plans = (Plans) unmarshaller.unmarshal(reader);
            return plans.getPlan();
        } catch (JAXBException  | FileNotFoundException e) {
            throw new ParserException(e.getMessage(),e);
        }

    }
}
