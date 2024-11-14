package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Seance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeanceDto {

    private Long id;
    private String titre;
    private Long idsection;


    public static Seance toEntity(SeanceDto seance) {
        return Seance.builder()
                .id(seance.getId())
                .titre(seance.getTitre())
                .build();
    }

    public static SeanceDto FromEntity(Seance seance) {
        return SeanceDto.builder()
                .id(seance.getId())
                .titre(seance.getTitre())
              //  .idsection(seance.getSection().getId())
                .build();

    }















}
