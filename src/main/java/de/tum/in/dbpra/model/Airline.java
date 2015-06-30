package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class Airline implements Serializable {

    private static final long serialVersionUID = -7621021308002792920L;

    private String code;
    private String name;
    private String callSign;

    @Override
    public int hashCode() {
        return (code != null)
                ? (this.getClass().hashCode() + code.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Airline) && (code != null)
                ? code.equals(((Airline) obj).code)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Airline[code=%d,name=%s,callSign=%s]",
                code, name, callSign);
    }
}
