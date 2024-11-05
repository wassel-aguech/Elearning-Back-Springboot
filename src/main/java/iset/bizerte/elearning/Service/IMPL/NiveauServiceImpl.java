package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Entity.Matiere;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.MatiereRepository;
import iset.bizerte.elearning.Repository.NiveauRepository;
import iset.bizerte.elearning.Service.NiveauService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;
    private final MatiereRepository matiereRepository;

    @Override
    public List<NiveauDto> findAll() {
        return niveauRepository.findAll().stream()
                .map(NiveauDto::FromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public NiveauDto findById(Long id) {
//        return niveauRepository.findById(id)
//                .map(NiveauDto::FromEntity)
//                .orElseThrow(() -> new RuntimeException("Niveau non trouver"));


        Optional<Niveau> niveau = niveauRepository.findById(id);
        if (niveau.isPresent()) {
            return NiveauDto.FromEntity(niveau.get());


        } else {
            throw new RuntimeException("Niveau not found");
        }


    }

    @Override
    public NiveauDto save(NiveauDto request) {
        Niveau niveau = NiveauDto.toEntity(request);
        List<Matiere> matierestoadd;
        if (request.getId_matieres().isEmpty()) {
            throw new IllegalArgumentException("you need to add matieres ");
        } else {
            matierestoadd = new ArrayList<>();
            for (Long Idmatieres : request.getId_matieres()) {
                Optional<Matiere> matierefound = matiereRepository.findById(Idmatieres);
                matierefound.ifPresent(matierestoadd::add);
            }
            System.err.println(matierestoadd);
            niveau.setMatieres(matierestoadd);

            Niveau niveauSaved = niveauRepository.save(niveau);
            return NiveauDto.FromEntity(niveauSaved);


        }
    }


    @Override
    public void deleteById(Long id) {


        Optional<Niveau> niveau = niveauRepository.findById(id);
        if (niveau.isPresent()) {
            niveauRepository.deleteById(id);


        } else {
            throw new RuntimeException("Niveau not found");
        }


    }

    @Override
    public List<NiveauDto> findbyobjet(String kye) {
        return niveauRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(NiveauDto::FromEntity)
                .collect(Collectors.toList());


    }

    @Override
    public List<NiveauDto> findDate(Date start, Date end) {
        return List.of();
    }






    public List<Matiere> getMatieresByNiveau(Long niveauId) {
        Niveau niveau = niveauRepository.findById(niveauId)
                .orElseThrow(() -> new IllegalArgumentException("Niveau not found"));
        return niveau.getMatieres();
    }




    @Override
    public NiveauDto uppdate(NiveauDto request) {
        Optional<Niveau> niveau = niveauRepository.findById(request.getId());
        if (niveau.isPresent()) {

            Niveau niveauconverted = NiveauDto.toEntity(request);

            niveau.get().setNiveaustudent(niveauconverted.getNiveaustudent());

            niveau.get().setOrientation(niveauconverted.getOrientation());
            List<Matiere> matierestoadd=this.getMatieresByNiveau(request.getId());
            if (request.getId_matieres().isEmpty()) {
                throw new IllegalArgumentException("you need to add matieres ");
            } else {
                //matierestoadd = new HashSet<>();
                for (Long Idmatieres : request.getId_matieres()) {
                    Optional<Matiere> matierefound = matiereRepository.findById(Idmatieres);
                    matierefound.ifPresent(matierestoadd::add);
                }
                System.err.println(matierestoadd);
                niveau.get().setMatieres(matierestoadd);

                Niveau niveauSaved = niveauRepository.save(niveau.get());


                return NiveauDto.FromEntity(niveau.get());


            }
        }
        else {
            throw new RuntimeException("Niveau not found");
        }
    }

    @Override
    public void removematierefromniveau(Long idniveau , Long idmatiere) {
        List<Matiere> matierestoremove=this.getMatieresByNiveau(idniveau);
        Optional<Matiere> matiere = matiereRepository.findById(idmatiere);
        Optional<Niveau> niveau = niveauRepository.findById(idniveau);
        if(matiere.isPresent() && niveau.isPresent()){
            Matiere mat = matiere.get();
            matierestoremove.removeIf(m -> m.equals(mat));
            niveau.get().setMatieres(matierestoremove);
            niveauRepository.save(niveau.get());



        }
    }

    @Override
    public List<MatiereDto> listmatierebyniveau(Long id) {
        return this.getMatieresByNiveau(id).stream()
                .map(MatiereDto::FromEntity)
                .collect(Collectors.toList());
    }


}
