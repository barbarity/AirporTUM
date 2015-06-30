package main.java.de.tum.in.dbpra.model;

/**
 * Created by barbarity on 30/06/15.
 */
public class CheckInWorker extends Person {

    private static final long serialVersionUID = 1476621643341778721L;

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CheckInWorker) && (id != null)
                ? id.equals(((CheckInWorker) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("CheckInWorker[id=%d,firstName=%s,lastName=%s,gender=%s,title=%s,birthdate=%s,address=%s,phone=%s,email=%s,passportId=%s,password=%s,salt=%s]",
                id, firstName, lastName, gender.getValue(), title, birthDate, address, phone, email, passportId, password, salt);
    }
}
