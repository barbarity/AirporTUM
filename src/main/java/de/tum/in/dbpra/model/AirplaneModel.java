package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;

/**
 * Created by barbarity on 30/06/15.
 */
public class AirplaneModel implements Serializable {

    private static final long serialVersionUID = -3940103851583211279L;

    private String id;
    private String name;
    private Integer range;
    private WakeType wake;

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

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public WakeType getWake() {
        return wake;
    }

    public void setWake(WakeType wake) {
        this.wake = wake;
    }

    public enum WakeType {
        LIGHT("light"),
        MEDIUM("medium"),
        HEAVY("heavy");

        private final String value;

        WakeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AirplaneModel) && (id != null)
                ? id.equals(((AirplaneModel) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("AirplaneModel[id=%d,name=%s,range=%d,wake=%s]",
                id, name, range, wake);
    }
}
