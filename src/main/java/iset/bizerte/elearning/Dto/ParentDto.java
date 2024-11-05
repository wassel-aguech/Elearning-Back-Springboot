package iset.bizerte.elearning.Dto;

import iset.bizerte.elearning.Entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParentDto  extends RegisterRequeste {

    private String cin;

    public static Parent toEntity(ParentDto request) {
        return Parent.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .cin(request.getCin())
                .build();
    }

    public static ParentDto fromEntity(Parent request) {
        return ParentDto.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .phone(request.getPhone())
                .cin(request.getCin())
                .build();
    }
}
