package iset.bizerte.elearning.Service;


import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.NiveauDto;

import java.util.Date;
import java.util.List;

public interface NiveauService {


     List<NiveauDto> findAll();
   NiveauDto findById(Long id);
    NiveauDto  save(NiveauDto   request);
    void deleteById(Long id);
   List<NiveauDto > findbyobjet(String kye);
    List<NiveauDto > findDate(Date start, Date end);
    NiveauDto uppdate(NiveauDto request);
    void removematierefromniveau (Long idniveau , Long idmatiere);

    List<MatiereDto> listmatierebyniveau (Long id);

































}
