package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.SupportDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface SupportService {




    List<SupportDto> findAll();
    SupportDto findById(Long id);
    SupportDto save(SupportDto   request);
    void deleteById(Long id);
    List<SupportDto > findbyobjet(String kye);
    List<SupportDto > findDate(Date start, Date end);
    SupportDto uppdate(SupportDto request);



    SupportDto uploadsupportfile(Long idsupport,  MultipartFile file  );

}



