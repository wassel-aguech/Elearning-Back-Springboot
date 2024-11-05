package iset.bizerte.elearning.Dto;


import iset.bizerte.elearning.Entity.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SectionDto {

    private String titre;
    private Long ordre;
    private String discription;
    private Long idcours;


    public static Section toEntity(SectionDto sectionDto) {

        return Section.builder()
                .titre(sectionDto.getTitre())
                .ordre(sectionDto.getOrdre())
                .discription(sectionDto.getDiscription())

                .build();

    }


    public static SectionDto FromEntity(Section sectionDto) {

        return SectionDto.builder()
                .titre(sectionDto.getTitre())
                .ordre(sectionDto.getOrdre())
                .discription(sectionDto.getDiscription())
                .idcours(sectionDto.getCours().getId())




                .build();





    }


}
