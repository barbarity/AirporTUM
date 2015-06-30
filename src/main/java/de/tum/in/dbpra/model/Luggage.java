package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by barbarity on 30/06/15.
 */
public class Luggage implements Serializable {

    private static final long serialVersionUID = -1400671671678509086L;

    private Integer id;
    private Booking booking;
    private Integer weight;
    private Integer height;
    private Integer width;
    private Integer length;
    private BigDecimal additionalCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(BigDecimal additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Luggage) && (id != null)
                ? id.equals(((Luggage) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Airline[id=%d,booking=%s,weight=%d,height=%d,width=%d,length=%d,additionalCost=%f]",
                id, booking, weight, height, width, length, additionalCost);
    }
}
