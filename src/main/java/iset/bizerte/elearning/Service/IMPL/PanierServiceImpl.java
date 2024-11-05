package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Dto.PanierDto;
import iset.bizerte.elearning.Entity.*;
import iset.bizerte.elearning.Repository.CoursRepository;
import iset.bizerte.elearning.Repository.EtudiantRepository;
import iset.bizerte.elearning.Repository.PanierRepository;
import iset.bizerte.elearning.Service.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final CoursRepository coursRepository;
    private final EtudiantRepository etudiantRepository;



    @Override
    public List<PanierDto> findAll() {
        return panierRepository.findAll().stream()
                .map(PanierDto::FromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public PanierDto findById(Long id) {
        Optional<Panier> panier = panierRepository.findById(id);
        if (panier.isPresent()) {
            return PanierDto.FromEntity(panier.get());
        }
        else {
            throw new RuntimeException("Cours not found");
        }
    }


    @Override
    public PanierDto save(PanierDto request) {
        Panier panier = PanierDto.ToEntity(request);
        Double totalprix = 0.0;
        int quantite = 0;
        Optional<Etudiant> etudiantselected = etudiantRepository.findById(request.getIdstudent());

        List<Cours> courstoadd;
        if (!request.getIdcourses().isEmpty() && etudiantselected.isPresent()) {
            courstoadd = new ArrayList<>();
            for (Long Idcourses : request.getIdcourses()) {
                Optional<Cours> coursfound = coursRepository.findById(Idcourses);
                totalprix = totalprix + coursfound.get().getPrix();
                quantite = quantite + 1;
                coursfound.ifPresent(courstoadd::add);
            }
            panier.setCours(courstoadd);
            panier.setEtudiant(etudiantselected.get());
            panier.setPrix(totalprix);
            panier.setQuantite(quantite);

return PanierDto.FromEntity(panierRepository.save(panier));
        }
        else {

            throw new RuntimeException("accepted");
        }

    }

    @Override
    public void deleteById(Long id) {
            Optional<Panier> panier = panierRepository.findById(id);
            if (panier.isPresent()) {
                panierRepository.deleteById(id);
            }
            else {
                throw new RuntimeException("panier not found");
            }
    }

    @Override
    public List<PanierDto> findbyobjet(String kye) {
        return panierRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(PanierDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PanierDto> findDate(Date start, Date end) {
        return List.of();
    }
}
