package iset.bizerte.elearning.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Niveau extends AbstractEntity {
    private String niveaustudent;

    private String orientation;

    //mappedBy = "niveau",
    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<Matiere> matieres = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "niveau", cascade = CascadeType.ALL)
    private List<Cours> cours = new ArrayList<>();
}


