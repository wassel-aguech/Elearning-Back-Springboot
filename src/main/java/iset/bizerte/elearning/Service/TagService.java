package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.TagDto;

import java.util.Date;
import java.util.List;

public interface TagService {


    List<TagDto> findAll();
    TagDto findById(Long id);
    TagDto save(TagDto   request);
    void deleteById(Long id);
    List<TagDto> findbyobjet(String kye);
    List<TagDto> findDate(Date start, Date end);
    TagDto uppdate(TagDto request);

}
