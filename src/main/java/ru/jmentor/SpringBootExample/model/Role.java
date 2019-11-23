package ru.jmentor.SpringBootExample.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roleTable")
public class Role {
    private Long id;
    private String userRole;
    private Set<User> users = new HashSet<>();

    public Role() { }

    public Role(Long id, String userRole) {
        this.id = id;
        this.userRole = userRole;
    }

    public Role(Role role) {
        this.id = role.getId();
        this.userRole = role.getUserRole();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }

    @Column(name = "userRole")
    public String getUserRole() { return userRole; }

    @ManyToMany
    @JoinTable(
            name = "userRole",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    public Set<User> getUsers() { return users; }

    public void setId(Long id) { this.id = id; }

    public void setUserRole(String userRole) { this.userRole = userRole; }

    public void setUsers(Set<User> users) { this.users = users; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return userRole.equals(role.userRole);
    }

    @Override
    public int hashCode() { return Objects.hash(userRole); }

    @Override
    public String toString() { return userRole; }
}
