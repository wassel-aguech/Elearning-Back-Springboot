package iset.bizerte.elearning.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("Enseignant")

public class Enseignant extends User{

    private String diplome;
    private String biographie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Administrateur administrateur  ;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enseignant", cascade = CascadeType.ALL)
    private Set<Matiere> matieres = new HashSet<>();

}
