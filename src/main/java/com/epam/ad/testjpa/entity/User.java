package com.epam.ad.testjpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT u from User u"),
        @NamedQuery(name = "User.findById", query = "SELECT u from User u WHERE u.id =:uid"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u from User u WHERE u.username =:uname")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Order> orders;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }
}
