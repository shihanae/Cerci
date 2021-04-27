package com.giftery.Model.Role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_seq")
    @Getter
    @Setter
    private long id;

    @Column(name="name")
    @Getter
    @Setter
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
}
