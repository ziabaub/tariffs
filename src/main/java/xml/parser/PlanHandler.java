package xml.parser;

import entity.*;
import factory.PlanFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PlanHandler extends DefaultHandler {

    private List<Plan> plans;
    private Plan currentPlan;
    private String currentElement;
    private boolean name;
    private Address currentAddress;
    private Company currentCompany;

    public PlanHandler() {
        plans = new ArrayList<>();
    }

    public List<Plan> getPlans() {
        return plans;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        currentElement = qName;

        if ("plan".equals(localName)) {
            name = false;
            PlanFactory planFactory = new PlanFactory();
            currentPlan = planFactory.getPlan(attrs.getValue(0));
        } else if ("company".equals(localName)) {
            name = true;
            currentCompany = new Company();
        } else if ("address".equals(localName)) {
            currentAddress = new Address();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (!value.isEmpty()) {
            if (currentElement.equals("name") && !name) {
                currentPlan.setName(value);
            } else if (currentElement.equals("dateOfIssue")) {
                currentPlan.setDateOfIssue(value);
            } else if (currentElement.equals("dateOfExpiry")) {
                currentPlan.setDateOfExpiry(value);
            } else if (currentElement.equals("status")) {
                currentPlan.setStatus(value);
            } else if (currentElement.equals("name") && name) {
                currentCompany.setName(value);
            } else if (currentElement.equals("country")) {
                currentAddress.setCountry(value);
            } else if (currentElement.equals("city")) {
                currentAddress.setCity(value);
            } else if (currentElement.equals("street")) {
                currentAddress.setStreet(value);
            } else if (currentElement.equals("rate")) {
                currentCompany.setRate(Integer.valueOf(value));
            } else if (currentElement.equals("minuteCost")) {
                currentPlan.setMinuteCost(Double.valueOf(value));
            } else if (currentElement.equals("smsCost")) {
                currentPlan.setSmsCost(Double.valueOf(value));
            } else if (currentElement.equals("gigabyteCost")) {
                currentPlan.setGigabyteCost(Double.valueOf(value));
            }
            fillSubPlan(currentElement, value);
        }
    }

    private void fillSubPlan(String currentElement, String value) {

        if (currentElement.equals("subscription")) {
            ((PostPaidPlan) currentPlan).setSubscription(value);
        } else if (currentElement.equals("fee")) {
            ((PostPaidPlan) currentPlan).setFee(Integer.valueOf(value));
        } else if (currentElement.equals("countOfMinutes")) {
            ((AudioPlan) currentPlan).setCountOfMinutes(Integer.valueOf(value));
        } else if (currentElement.equals("countOfGigabytes")) {
            ((DataPlan) currentPlan).setCountOfGigabytes(Integer.valueOf(value));
        } else if (currentElement.equals("balance")) {
            ((PrepaidPlan) currentPlan).setBalance(Integer.valueOf(value));
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {

        if ("plan".equals(localName)) {
            currentCompany.setAddress(currentAddress);
            currentPlan.setCompany(currentCompany);
            plans.add(currentPlan);
        }
    }

}
