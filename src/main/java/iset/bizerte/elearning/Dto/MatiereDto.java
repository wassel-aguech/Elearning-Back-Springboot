package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Matiere;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MatiereDto {

    private Long id;
    private String libelle;

    private Long idensegnant;


    public static Matiere toEntity(MatiereDto matiere) {
        return Matiere.builder()
                .libelle(matiere.getLibelle())
                .id(matiere.getId())
                .build();
    }

    public static MatiereDto FromEntity(Matiere matiere) {
        return MatiereDto.builder()
                .libelle(matiere.getLibelle())
                .id(matiere.getId())
                .build();
    }










}
