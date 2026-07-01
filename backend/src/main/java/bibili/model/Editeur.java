package bibili.model;

import com.fasterxml.jackson.annotation.JsonView;

import bibili.view.Views;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @JsonView(Views.Editeur.class)
    private Integer id;

    @Column(length = 50, nullable = false)
    @JsonView(Views.Editeur.class)
    private String nom;

    @Column(length = 50, nullable = false)
    @JsonView(Views.Editeur.class)
    private String pays;
      

        public Editeur() {
        }


        public Editeur(Integer id, String nom, String pays) {
            this.id = id;
            this.nom = nom;
            this.pays = pays;
        }


        public Integer getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPays() {
            return pays;
        }


        public void setId(Integer id) {
            this.id = id;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPays(String pays) {
            this.pays = pays;
        }


        @Override
        public String toString() {
            return "Editeur [id=" + id + ", nom=" + nom + ", pays=" + pays + "]";
        }

}
