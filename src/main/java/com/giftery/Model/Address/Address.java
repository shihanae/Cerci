package com.giftery.Model.Address;

import com.giftery.Model.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="address")
public class Address
{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    @SequenceGenerator(name="address_generator", sequenceName = "address_seq")
    @Getter
    @Setter
    private long id;

    @Column(name="address_line_one")
    @Getter
    @Setter
    private String addressLineOne;

    @Column(name="address_line_two")
    @Getter
    @Setter
    private String addressLineTwo;

    @Column(name="postal_code")
    @Getter
    @Setter
    private String postalCode;

    @Column(name="city")
    @Getter
    @Setter
    private String city;

    @Column(name="state")
    @Getter
    @Setter
    private String state;

    @Column(name="country_code")
    @Getter
    @Setter
    private String countryCode;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    @Getter
    @Setter
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressLineOne.equals(address.addressLineOne) && Objects.equals(addressLineTwo, address.addressLineTwo) && postalCode.equals(address.postalCode) && city.equals(address.city) && state.equals(address.state) && countryCode.equals(address.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLineOne, addressLineTwo, postalCode, city, state, countryCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", user=" + user +
                '}';
    }

    public Address() {
    }

    public Address(String addressLineOne, String addressLineTwo, String postalCode, String city, String state, String countryCode, User user) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.countryCode = countryCode;
        this.user = user;
    }

    public Address(String addressLineOne, String postalCode, String city, String state, String countryCode, User user) {
        this.addressLineOne = addressLineOne;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.countryCode = countryCode;
        this.user = user;
    }
}
