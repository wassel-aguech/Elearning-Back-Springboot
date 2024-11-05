package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.NiveauDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface CoursService {

    List<CoursDto> findAll();
    CoursDto findById(Long id);
    CoursDto  save(CoursDto request);
    void deleteById(Long id);
    List<CoursDto > findbyobjet(String kye);
    List<CoursDto> findDate(Date start, Date end);
    CoursDto uppdate(CoursDto request);
    Void addcoursestostudent( Long id);
List<CoursDto> listcoursbyidniveauetidmatiere(Long idmatiere, Long idniveau);
    CoursDto uploadcoursfile(Long Idcours, MultipartFile image , MultipartFile file  );

}
