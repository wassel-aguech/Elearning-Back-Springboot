package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Niveau;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NiveauDto {
    public Long id;
    private String niveaustudent;
    private String orientation;

    List<Long> id_matieres;




    public static Niveau toEntity(NiveauDto niveau ) {

        return Niveau.builder()
                .niveaustudent(niveau.getNiveaustudent())
                .orientation(niveau.getOrientation())
                .id(niveau.getId())


                .build();



    }

    public static NiveauDto FromEntity(Niveau niveau) {

        return NiveauDto.builder()
                .niveaustudent(niveau.getNiveaustudent())

                .orientation(niveau.getOrientation())
                .id(niveau.getId())


                .build();






    }













}
