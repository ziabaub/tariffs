package xml.parser;

import entity.Plan;
import exception.ParserException;

import java.util.List;

public interface XmlParser {
    List<Plan> parse(String path) throws ParserException;

}
