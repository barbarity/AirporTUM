package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class Country implements Serializable {

    private static final long serialVersionUID = 3148692615639978851L;

    private String code;
    private String name;
    private Currency currency;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        return (code != null)
                ? (this.getClass().hashCode() + code.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Country) && (code != null)
                ? code.equals(((Country) obj).code)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Country[id=%d,name=%s,currency=%s]",
                code, name, currency);
    }
}
