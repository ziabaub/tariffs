package director;

import entity.*;
import exception.ParserException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import validator.ValidatorSAX;
import xml.parser.DomParser;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class DirectorTest {

    @Mock
    private DomParser domParser;
    @Mock
    private ValidatorSAX validatorSAX;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void runShouldCheckWholeTask() throws  ParserException {
        //given
        Director director = new Director(domParser, validatorSAX);
        String xmlPath = "src/test/java/resources/plans.xml";
        String xsdPath = "src/test/java/resources/plan.xsd";

        //expected
        AudioPlan plan1 = new AudioPlan();
        plan1.setName("Unlimit");
        plan1.setDateOfIssue("30/1/2019");
        plan1.setDateOfExpiry("29/1/2020");
        plan1.setStatus("not active");
        Company company = new Company();
        company.setName("MTS");
        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Nyamiga");
        company.setAddress(address);
        company.setRate(4);
        plan1.setCompany(company);
        plan1.setMinuteCost(0.487);
        plan1.setSmsCost(0.151);
        plan1.setGigabyteCost(2.592);
        plan1.setSubscription("weekly");
        plan1.setFee(215);
        plan1.setCountOfMinutes(240);

        DataPlan plan2 = new DataPlan();
        plan2.setName("Comfort");
        plan2.setDateOfIssue("30/1/2019");
        plan2.setDateOfExpiry("30/1/2020");
        plan2.setStatus("active");
        Company company2 = new Company();
        company2.setName("Alfa");
        Address address2 = new Address();
        address2.setCountry("Belarus");
        address2.setCity("Minsk");
        address2.setStreet("Nezavisimosty");
        company2.setAddress(address2);
        company2.setRate(4);
        plan2.setCompany(company2);
        plan2.setMinuteCost(0.487);
        plan2.setSmsCost(0.151);
        plan2.setGigabyteCost(2.592);
        plan2.setSubscription("weekly");
        plan2.setFee(113);
        plan2.setCountOfGigabytes(32);
        List<Plan> expected = Arrays.asList(plan1, plan2);

        validatorSAX.valid(xmlPath, xsdPath);

        //when
        when(validatorSAX.valid(xmlPath, xsdPath)).thenReturn(true);
        when(domParser.parse(xmlPath)).thenReturn(expected);

        List<Plan> actually = director.process(xmlPath);

        //then
        Assert.assertEquals(expected,actually);
        verify(validatorSAX).valid(xmlPath, xsdPath);
        verify(domParser).parse(xmlPath);

    }
}