package iset.bizerte.elearning.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder


public class Panier extends  AbstractEntity{



    private String nom;
    private Double prix;
    private int quantite;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToMany (fetch = FetchType.LAZY )
    private List<Cours> cours  = new ArrayList<>();

}
