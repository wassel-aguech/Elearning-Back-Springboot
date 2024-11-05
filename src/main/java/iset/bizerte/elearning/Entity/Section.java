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
public class Section extends AbstractEntity{

    private String titre;
    private Long ordre;
    private String discription;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id")
    private Cours cours;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Seance> seances = new HashSet<>();
}




