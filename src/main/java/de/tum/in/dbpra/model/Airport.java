package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class Airport implements Serializable {

    private static final long serialVersionUID = 1870404047871419348L;

    private String IATA;
    private String name;
    private City city;
    private Integer foundationYear;

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public int hashCode() {
        return (IATA != null)
                ? (this.getClass().hashCode() + IATA.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Airport) && (IATA != null)
                ? IATA.equals(((Airport) obj).IATA)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Airport[IATA=%d,name=%s,city=%s,foundationYear=%d]",
                IATA, name, city, foundationYear);
    }
}
