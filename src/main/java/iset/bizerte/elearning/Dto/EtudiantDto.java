package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EtudiantDto  extends RegisterRequeste {

    private String classe ;
    private String section ;
    public static Etudiant toEntity(EtudiantDto request) {
        return  Etudiant.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .classe(request.getClasse())
                .section(request.getSection())
                .build();
    }

    public static EtudiantDto fromEntity(Etudiant request) {
        return EtudiantDto.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .classe(request.getClasse())
                .section(request.getSection())
                .build();
    }
}
