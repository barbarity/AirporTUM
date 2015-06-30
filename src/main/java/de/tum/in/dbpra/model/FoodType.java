package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class FoodType implements Serializable {

    private static final long serialVersionUID = 5569056474591579082L;

    private String code;
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return (code != null)
                ? (this.getClass().hashCode() + code.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FoodType) && (code != null)
                ? code.equals(((FoodType) obj).code)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("FoodType[code=%d,name=%s,description=%s]",
                code, name, description);
    }
}
