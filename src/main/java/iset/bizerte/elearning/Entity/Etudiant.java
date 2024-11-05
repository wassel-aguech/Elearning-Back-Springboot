package iset.bizerte.elearning.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("Etudiant")

public class Etudiant extends User {

    private String classe ;
    private String section ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Parent parent ;

    @ManyToMany(fetch = FetchType.LAZY)

    private List<Cours> cours = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "etudiant")
    private List<Panier> paniers = new ArrayList<>();



}
