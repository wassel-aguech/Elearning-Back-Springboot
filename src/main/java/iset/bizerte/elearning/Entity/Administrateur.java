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
@DiscriminatorValue("Admin")

public class Administrateur extends User{
    private boolean isadmine;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrateur", cascade = CascadeType.ALL)
    Set<Enseignant> enseignants=new HashSet<>();
}

