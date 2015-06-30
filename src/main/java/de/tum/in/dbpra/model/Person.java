package main.java.de.tum.in.dbpra.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by barbarity on 30/06/15.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -2838117565577856762L;

    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected GenderType gender;
    protected String title;
    protected Date birthDate;
    protected String address;
    protected String phone;
    protected String email;
    protected String passportId;
    protected String password;
    protected String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public enum GenderType {
        MALE("male"),
        FEMALE("female");

        private final String value;

        GenderType(String value) {
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
        return (obj instanceof Person) && (id != null)
                ? id.equals(((Person) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d,firstName=%s,lastName=%s,gender=%s,title=%s,birthdate=%s,address=%s,phone=%s,email=%s,passportId=%s,password=%s,salt=%s]",
                id, firstName, lastName, gender, title, birthDate, address, phone, email, passportId, password, salt);
    }
}
