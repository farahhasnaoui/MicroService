package com.example.candidats.candidat.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String objectif;
    private String niveau;
    private int dureeEnSemaines;
//

    @JsonIgnore
    @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
    private List<Activite> activites;

    public Programme() {
    }

    public Programme(Long id, String nom, String description, String objectif, String niveau, int dureeEnSemaines, List<Activite> activites) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.objectif = objectif;
        this.niveau = niveau;
        this.dureeEnSemaines = dureeEnSemaines;
        this.activites = activites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getDureeEnSemaines() {
        return dureeEnSemaines;
    }

    public void setDureeEnSemaines(int dureeEnSemaines) {
        this.dureeEnSemaines = dureeEnSemaines;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }
}

