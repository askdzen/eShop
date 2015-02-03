package com.epam.ad.testjpa.entity;


import javax.persistence.*;

import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(name = "Role.getAll", query = "SELECT r from Role r"),
        @NamedQuery(name = "Role.findById", query = "SELECT r from Role r WHERE r.id =:rid"),
        @NamedQuery(name = "Role.findByName", query = "SELECT r from Role r WHERE r.name =:rname")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
