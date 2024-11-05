package iset.bizerte.elearning.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Matiere extends AbstractEntity{
    private String libelle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matieres", cascade = CascadeType.ALL)
    private Set<Cours> cours = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "matieres", cascade = CascadeType.ALL)
    private Set<Niveau> niveaux = new HashSet<>();






}






