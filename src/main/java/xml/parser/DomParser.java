package xml.parser;

import builder.PlanBuilder;
import entity.Plan;
import exception.ParserException;

import java.util.List;

public class DomParser implements XmlParser {

    @Override
    public List<Plan> parse(String path) throws ParserException {
        PlanBuilder planBuilder = new PlanBuilder();
        planBuilder.build(path);
        return planBuilder.getPlans();
    }
}
