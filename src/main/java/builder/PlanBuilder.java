package builder;

import entity.*;
import exception.ParserException;

import factory.PlanFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanBuilder {
    private List<Plan> plans;
    private Plan plan;

    public List<Plan> getPlans() {
        return plans;
    }

    public void build(String path) throws ParserException {
        try {
            plans = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("plan");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                plans.add(buildPlan(element));
            }
        }  catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException(e.getMessage() , e);
        }
    }

    private Plan buildPlan(Element e) {

        Attr attributeNode = e.getAttributeNode("xsi:type");
        String planType = attributeNode.getValue();

        PlanFactory planFactory = new PlanFactory();
        plan = planFactory.getPlan(planType);
        Company company = new Company();
        Address address = new Address();

        String country = getElementTextContent(e, "country", 0);
        address.setCountry(country);
        String city = getElementTextContent(e, "city", 0);
        address.setCity(city);
        String street = getElementTextContent(e, "street", 0);
        address.setStreet(street);

        company.setAddress(address);
        String nameCompany = getElementTextContent(e, "name", 1);
        company.setName(nameCompany);
        int rate = Integer.parseInt(getElementTextContent(e, "rate", 0));
        company.setRate(rate);

        plan.setCompany(company);
        String name = getElementTextContent(e, "name", 0);
        plan.setName(name);
        String dateOfIssue = getElementTextContent(e, "dateOfIssue", 0);
        plan.setDateOfIssue(dateOfIssue);
        String dateOfExpiry = getElementTextContent(e, "dateOfExpiry", 0);
        plan.setDateOfExpiry(dateOfExpiry);
        String status = getElementTextContent(e, "status", 0);
        plan.setStatus(status);
        double minuteCost = Double.parseDouble(getElementTextContent(e, "minuteCost", 0));
        plan.setMinuteCost(minuteCost);
        double smsCost = Double.parseDouble(getElementTextContent(e, "smsCost", 0));
        plan.setSmsCost(smsCost);
        String gigabyteCost = getElementTextContent(e, "gigabyteCost", 0);
        plan.setGigabyteCost(Double.valueOf(gigabyteCost));
        fillSubPlan(e, planType);

        return plan;
    }

    private void fillSubPlan(Element e, String type) {


        String dataType = "AudioPlan , DataPlan , FullPlan";
        if (dataType.contains(type)) {
            String subscription = getElementTextContent(e, "subscription", 0);
            ((PostPaidPlan) plan).setSubscription(subscription);
            String fee = getElementTextContent(e, "fee", 0);
            ((PostPaidPlan) plan).setFee(Integer.valueOf(fee));
        }


        if ("AudioPlan".equals(type)) {
            String countOfMinutes = getElementTextContent(e, "countOfMinutes", 0);
            ((AudioPlan) plan).setCountOfMinutes(Integer.valueOf(countOfMinutes));
        }
        if ("DataPlan".equals(type)) {
            String countOfGigabytes = getElementTextContent(e, "countOfGigabytes", 0);
            ((DataPlan) plan).setCountOfGigabytes(Integer.valueOf(countOfGigabytes));
        }
        if ("FullPlan".equals(type)) {
            String countOfGigabytes = getElementTextContent(e, "countOfGigabytes", 0);
            String countOfMinutes = getElementTextContent(e, "countOfMinutes", 0);

            ((FullPlan) plan).setCountOfGigabytes(Integer.valueOf(countOfGigabytes));
            ((FullPlan) plan).setCountOfMinutes(Integer.valueOf(countOfMinutes));
        }
    }

    private String getElementTextContent(Element element, String elementName, int index) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(index);
        return node.getTextContent();

    }

}
