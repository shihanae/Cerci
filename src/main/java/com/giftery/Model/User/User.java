package com.giftery.Model.User;

import com.giftery.Model.Address.Address;
import com.giftery.Model.Role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User
{
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceIdGenerator") // Can this be the same for every entity?
    @SequenceGenerator(name="SequenceIdGenerator", sequenceName = "users_seq", allocationSize = 1)
    @Getter
    @Setter
    private long id;

    @Column(name = "username", nullable = false, length = 100)
    @Getter
    @Setter
    private String username;

    @Column(name = "email", nullable = false, length=100)
    @Getter
    @Setter
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    @Getter
    @Setter
    private String password;

    @Column(name = "first_name", nullable = true, length = 100)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 100)
    @Getter
    @Setter
    private String middleName;

    @Column(name = "last_name", nullable = true, length = 100)
    @Getter
    @Setter
    private String lastName;

    @Column(name = "phone_number", nullable = true, length = 20)
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name = "active", columnDefinition = "boolean default true", nullable = false)
    @Getter
    @Setter
    private boolean active = true;


    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    @Getter
    @Setter
    private boolean deleted = false;

    @Column(name = "locked", columnDefinition = "boolean default false", nullable = false)
    @Getter
    @Setter
    private boolean locked = false;

    @Column(name = "date_created", nullable = false)
    @Getter
    @Setter
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "date_updated", nullable = false)
    @Getter
    @Setter
    private LocalDateTime dateUpdated = LocalDateTime.now();

    @Column(name = "last_seen", nullable = true)
    @Getter
    @Setter
    private LocalDateTime lastSeen;

   /* @Column(name = "account_type" , nullable = false, length = 1)
    @Getter
    @Setter
    private int accountType; // 0 - Basic | 1 - Premium | 2 - Charity?*/

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name="users_friends",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id", updatable = true, insertable = true),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id", updatable = true, insertable = true))
    @Getter
    @Setter
    private List<User> friends;

    @Column(name = "user_code", nullable = true, length = 255)
    @Getter
    @Setter
    private String userCode;

    @OneToMany(mappedBy="user",
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Getter
    @Setter
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @Getter
    @Setter
    @JoinTable(name="users_roles",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id", updatable = true, insertable = true),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", updatable = true, insertable = true))
    private List<Role> roles;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addAddress(Address address)
    {
        if (addresses == null) {
            addresses = new ArrayList<Address>();
        }
        addresses.add(address);
    }

    public void addRole(Role role)
    {
        if(roles == null)
        {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    public void deleteRole(Role role)
    {
        if(roles != null && roles.contains(role))
        {
            roles.remove(role);
        }
    }

    public void removeAddress(Address address)
    {
        addresses.remove(address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
