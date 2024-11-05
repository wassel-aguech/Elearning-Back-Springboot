package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Tag_;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TagDto {
    private String libelle;
    private Boolean deleted;


    public static Tag_ toEntity(TagDto tag) {

        return  Tag_.builder()

                .libelle(tag.getLibelle())
                .deleted(tag.getDeleted())

                .build();




    }



    public static TagDto FromEntity(Tag_ tag) {

        return  TagDto.builder()

                .libelle(tag.getLibelle())
                .deleted(tag.getDeleted())

                .build();




    }







}
