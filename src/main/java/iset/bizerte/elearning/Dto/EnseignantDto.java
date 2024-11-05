package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EnseignantDto extends RegisterRequeste {
    private String diplome;
    private String biographie;
    public static Enseignant toEntity(EnseignantDto request) {
        return Enseignant.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .diplome(request.getDiplome())
                .biographie(request.getBiographie())
                .build();
    }

    public static EnseignantDto fromEntity(Enseignant request) {
        return EnseignantDto.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .diplome(request.getDiplome())
                .biographie(request.getBiographie())

                .build();
    }
}
