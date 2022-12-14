package com.javabatista.ciclovida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<CyclingDay> cyclingDays = new HashSet<>();
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<CyclingMonth> cyclingMonths = new HashSet<>();
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    public User(){
    }
    public User(String username){
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CyclingDay> getCyclingDays() {
        return cyclingDays;
    }

    public void setCyclingDays(Set<CyclingDay> cyclingDays) {
        this.cyclingDays = cyclingDays;
    }

    public void setCyclingDay(CyclingDay cyclingDay) {
        cyclingDay.setUser(this);
        this.cyclingDays.add(cyclingDay);
    }

    public Set<CyclingMonth> getCyclingMonths() {
        return cyclingMonths;
    }

    public void setCyclingMonths(Set<CyclingMonth> cyclingMonths) {
        this.cyclingMonths = cyclingMonths;
    }

    public void setCyclingMonth(CyclingMonth cyclingMonth) {
        cyclingMonth.setUser(this);
        this.cyclingMonths.add(cyclingMonth);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
