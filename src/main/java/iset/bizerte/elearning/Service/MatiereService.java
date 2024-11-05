package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.NiveauDto;

import java.util.Date;
import java.util.List;

public interface MatiereService {


    List<MatiereDto> findAll();
    MatiereDto findById(Long id);
    MatiereDto  save(MatiereDto   request);
    void deleteById(Long id);
    List<MatiereDto > findbyobjet(String kye);
    List<MatiereDto > findDate(Date start, Date end);










}
