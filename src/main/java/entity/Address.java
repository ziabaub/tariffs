//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.30 at 01:30:43 AM MSK 
//


package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = {
    "country",
    "city",
    "street"
})
public class Address {

    @XmlElement(required = true)
    private String country;
    @XmlElement(required = true)
    private String city;
    @XmlElement(required = true)
    private String street;


    public String getCountry() {
        return country;
    }


    public void setCountry(String value) {
        this.country = value;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String value) {
        this.city = value;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String value) {
        this.street = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street);
    }
}
