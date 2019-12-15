package director;

import entity.Plan;
import exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validator.ValidatorSAX;
import xml.parser.*;
import xml.parser.XmlParser;


import java.util.ArrayList;
import java.util.List;


public class Director {
    private final Logger logger = LogManager.getLogger(Director.class);
    private XmlParser parser;
    private ValidatorSAX validatorSAX;

    public Director(DomParser domParser, ValidatorSAX validatorSAX) {
        this.parser = domParser;
        this.validatorSAX = validatorSAX;
    }

    public List<Plan> process(String path) {
        String xsd = this.getClass().getResource("/plan.xsd").getPath();
        try {
            validatorSAX.valid(path, xsd);
            return parser.parse(path);
        } catch (ParserException e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

}
