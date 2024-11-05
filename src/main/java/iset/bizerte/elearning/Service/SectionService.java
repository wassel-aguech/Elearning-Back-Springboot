package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Entity.Section;

import java.util.Date;
import java.util.List;

public interface SectionService {




    List<SectionDto> findAll();
    SectionDto findById(Long id);
    SectionDto save(SectionDto   request);
    void deleteById(Long id);
    List<SectionDto > findbyobjet(String kye);
    List<SectionDto > findDate(Date start, Date end);
    SectionDto uppdate(SectionDto request);










}
