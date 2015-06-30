package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by barbarity on 30/06/15.
 */
public class FlightSegmentTicket implements Serializable {

    private static final long serialVersionUID = -7621021308002792920L;

    private String id;
    private Booking booking;
    private Flight flight;
    private FoodType foodType;
    private FSTClassType classType;
    private BigDecimal price;
    private Integer seatNumber;

    public enum FSTClassType {
        BUSINESS("business"),
        ECONOMY("economy"),
        FIRST("first");

        private final String value;

        FSTClassType(String value) {
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
        return (obj instanceof FlightSegmentTicket) && (id != null)
                ? id.equals(((FlightSegmentTicket) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("FlightSegmentTicket[id=%d,booking=%s,flight=%s,foodType=%s,classType=%s,price=%f,seatNumber=%d]",
                id, booking, flight, foodType, classType, price, seatNumber);
    }
}
