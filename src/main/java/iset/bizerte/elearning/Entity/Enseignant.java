package iset.bizerte.elearning.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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

}
