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

    private Long id;
    private Long ordre;
    private String name;
    private String urlSupport;
    private Long idseance;


    public static Support toEntity(SupportDto support) {
        return Support.builder()
                .ordre(support.getOrdre())
                .name(support.getName())
                .urlSupport(support.getUrlSupport())
                .build();

    }
    public static SupportDto FromEntity(Support support) {
        return SupportDto.builder()
                .id(support.getId())
                .ordre(support.getOrdre())
                .name(support.getName())
                .urlSupport(support.getUrlSupport())
                .build();

    }







}
