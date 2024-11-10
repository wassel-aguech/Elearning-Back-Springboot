package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Panier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class PanierDto {

    private Long id;
    private String nom;
    private Double prix;
    private int quantite;
    private List<Long> idcourses;
    private Long idstudent;


    public static Panier ToEntity(PanierDto request){
        return Panier.builder()
                .id(request.getId())
                .nom(request.getNom())
                .prix(request.getPrix())
                .quantite(request.getQuantite())
                .build();
    }


    public static PanierDto FromEntity(Panier request){
        return PanierDto.builder()
                .id(request.getId())
                .nom(request.getNom())
                .prix(request.getPrix())
                .quantite(request.getQuantite())
                .build();
    }


}
