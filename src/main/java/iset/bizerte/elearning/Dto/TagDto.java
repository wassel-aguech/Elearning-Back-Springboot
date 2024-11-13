package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TagDto {
    private Long id;
    private String libelle;
    private Boolean deleted;
    private Long idcours;




    public static Tag toEntity(TagDto tag) {
        return  Tag.builder()
                .libelle(tag.getLibelle())
                .deleted(tag.getDeleted())
                .build();
    }

    public static TagDto FromEntity(Tag tag) {
        return  TagDto.builder()
                .libelle(tag.getLibelle())
                .deleted(tag.getDeleted())
                .build();
    }







}
