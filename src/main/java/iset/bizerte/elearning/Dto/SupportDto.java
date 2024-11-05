package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Support;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SupportDto {



    private Boolean deleted;
    private Long ordre;
    private String name;
    private String paragraph;


    public static Support toEntity(SupportDto support) {


        return Support.builder()
                .deleted(support.getDeleted())
                .ordre(support.getOrdre())
                .name(support.getName())
                .paragraph(support.getParagraph())

                .build();

    }






    public static SupportDto FromEntity(Support support) {


        return SupportDto.builder()

                .ordre(support.getOrdre())
                .name(support.getName())
                .paragraph(support.getParagraph())

                .build();

    }







}
