package iset.bizerte.elearning.Dto;


import iset.bizerte.elearning.Entity.Cours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CoursDto {

    private Double prix;
    private Long id;
    private String titre;
    private boolean estouverte;
    private String description;
    private String urlimage;
    private String urlcours;
    private Long idniveau;
    private Long idmatiere;
    private Long idenseignant;
    private List <Long> tagid;
    private List <Long> sectionid;



    public static Cours ToEntity(CoursDto cours) {
        return Cours.builder()
                .titre(cours.getTitre())
                .estouverte(cours.isEstouverte())
                .urlimage(cours.getUrlimage())
                .description(cours.getDescription())
                .urlcours(cours.getUrlcours())
                .prix(cours.getPrix())
                .build();
    }


    public static CoursDto FromEntity(Cours cours) {
        return CoursDto.builder()
                .titre(cours.getTitre())
                .estouverte(cours.isEstouverte())
                .urlimage(cours.getUrlimage())
                .description(cours.getDescription())
                .urlcours(cours.getUrlcours())
                .prix(cours.getPrix())
                .build();
    }
}
