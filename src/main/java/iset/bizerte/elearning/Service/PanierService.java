package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.PanierDto;

import java.util.Date;
import java.util.List;

public interface PanierService {


    List<PanierDto> findAll();
  PanierDto findById(Long id);
   PanierDto  save(PanierDto request);
    void deleteById(Long id);
    List<PanierDto > findbyobjet(String kye);
    List<PanierDto > findDate(Date start, Date end);








}
