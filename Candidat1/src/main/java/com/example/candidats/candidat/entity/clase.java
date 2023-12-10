package com.example.candidats.candidat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class clase implements Serializable {

    private static final long serialVersionUID = 795450928237931201L;

    @Id
    @GeneratedValue
    private int id;
    private String nom, etage, block;

    @OneToMany(mappedBy = "clas")
    @JsonIgnore
    private List<Eleves> eleve;

//    @Column(name = "classe_id") // Champ pour stocker l'ID de la classe
//    private String classeId;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtage() {
        return etage;
    }
    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }


    public clase() {
        super();
        // TODO Auto-generated constructor stub
    }
    public clase(String nom, String etage, String block) {
        super();
        this.nom = nom;
        this.etage = etage;
        this.block = block;
    }



}
