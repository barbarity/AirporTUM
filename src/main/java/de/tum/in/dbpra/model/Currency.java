package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class Currency implements Serializable {

    private static final long serialVersionUID = -80928303196295122L;

    private String code;
    private String name;
    private String symbol;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return (code != null)
                ? (this.getClass().hashCode() + code.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Currency) && (code != null)
                ? code.equals(((Currency) obj).code)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Currency[id=%d,name=%s,symbol=%s]",
                code, name, symbol);
    }
}
