package com.authorization.authorizationserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Integer id;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<>(0);

    public Role() {
    }

    public Integer id() {
        return id;
    }

    public String description() {
        return description;
    }
}
