package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by barbarity on 30/06/15.
 */
public class Booking implements Serializable {

    private static final long serialVersionUID = 8988345800983365226L;

    private Integer id;
    private Person person;
    private Airline airline;
    private BigDecimal price;
    private Timestamp bookingTimestamp;
    private Currency currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getBookingTimestamp() {
        return bookingTimestamp;
    }

    public void setBookingTimestamp(Timestamp bookingTimestamp) {
        this.bookingTimestamp = bookingTimestamp;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Booking) && (id != null)
                ? id.equals(((Booking) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Booking[id=%d,Person=%s,airline=%s,price=%f,bookingTimestamp=%s,currency=%s]",
                id, person, airline, price, bookingTimestamp, currency);
    }
}
