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


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cours extends AbstractEntity {
    private String titre;
    private String description;
    private boolean estouverte;
    private String urlcours;
    private Double prix;
    private String urlimage;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Tag_> tags = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matere_id")
    private Matiere matieres;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Section> sections = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Enseignant teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Niveau-id")
    private Niveau niveau;
    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "cours")
    private List<Etudiant> etudiants = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "cours")
    private List<Panier> paniers = new ArrayList<>();





}



