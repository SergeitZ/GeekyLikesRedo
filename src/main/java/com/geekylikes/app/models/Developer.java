package com.geekylikes.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Developer {
    @Id
    @GeneratedValue
    public long id;
    private String name;
    private String email;
    private Integer cohort;
//    private String[] languages;
    @OneToMany
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    @JsonIgnore
    private List<Geekout> geekouts;

    @ManyToMany
    @JoinTable(
            name = "developer_language",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    @JsonIncludeProperties("name")
    public Set<Language> languages = new HashSet<>();

    public Developer() {}

    public Developer(String name, String email, Integer cohort, List<Geekout> geekouts, Set<Language> languages) {
        this.name = name;
        this.email = email;
        this.cohort = cohort;
        this.geekouts = geekouts;
        this.languages = languages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCohort() {
        return cohort;
    }

    public void setCohort(Integer cohort) {
        this.cohort = cohort;
    }

    public List<Geekout> getGeekouts() {
        return geekouts;
    }

    public void setGeekouts(List<Geekout> geekouts) {
        this.geekouts = geekouts;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }
}
