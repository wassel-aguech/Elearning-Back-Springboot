package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.SeanceDto;
import iset.bizerte.elearning.Dto.SeanceDto;

import java.util.Date;
import java.util.List;

public interface SeanceService {

    List<SeanceDto> findAll();
    SeanceDto findById(Long id);
    SeanceDto save(SeanceDto   request);
    void deleteById(Long id);
    List<SeanceDto > findbyobjet(String kye);
    List<SeanceDto > findDate(Date start, Date end);
    SeanceDto uppdate(SeanceDto request);
   
    








}
