package com.geekylikes.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Developer {
    @Id
    @GeneratedValue
    public long id;
    private String name;
    private String email;
    private Integer cohort;
    private String[] languages;
    @OneToMany
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private List<Geekout> geekouts;

    public Developer() {}

    public Developer(String name, String email, Integer cohort, String[] languages) {
        this.name = name;
        this.email = email;
        this.cohort = cohort;
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

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
}
