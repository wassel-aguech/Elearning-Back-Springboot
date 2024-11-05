package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Administrateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdministrateurDto  extends RegisterRequeste {
    private boolean isadmine;

    public static Administrateur toEntity(AdministrateurDto request) {
        return Administrateur.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();
    }
    public static AdministrateurDto fromEntity(Administrateur request) {
        return AdministrateurDto.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();
    }

}
