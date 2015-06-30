package main.java.de.tum.in.dbpra.model;

import org.postgresql.util.PGInterval;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class City implements Serializable {

    private static final long serialVersionUID = -7957045649211797770L;

    private String id;
    private String name;
    private Country country;
    private String postcode;
    private PGInterval interval;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public PGInterval getInterval() {
        return interval;
    }

    public void setInterval(PGInterval interval) {
        this.interval = interval;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof City) && (id != null)
                ? id.equals(((City) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("City[id=%d,name=%s,country=%s,postcode=%s,interval=%s]",
                id, name, country, postcode, interval);
    }
}
