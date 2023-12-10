
package com.example.candidats.candidat.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Eleves implements Serializable {

    private static final long serialVersionUID = 795450928237931201L;

    @Id
    @GeneratedValue
    private int id;
    private String nom, prenom, email;

    @ManyToOne
    clase clas;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Eleves() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Eleves(String nom, String prenom, String email) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }



}
