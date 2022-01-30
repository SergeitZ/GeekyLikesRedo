package com.geekylikes.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY)
//    private List<Geekout> geekouts;

    @ManyToMany
    @JoinTable(
            name = "developer_language",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
  @JsonIgnoreProperties("developers")
    public Set<Language> languages = new HashSet<>();

    @OneToOne
    private Avatar avatar;

    public Developer() {}

    public Developer(String name, String email, Integer cohort) {
        this.name = name;
        this.email = email;
        this.cohort = cohort;
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

//    public List<Geekout> getGeekouts() {
//        return geekouts;
//    }
//
//    public void setGeekouts(List<Geekout> geekouts) {
//        this.geekouts = geekouts;
//    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
