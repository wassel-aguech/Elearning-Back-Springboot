package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Entity.Enseignant;
import iset.bizerte.elearning.Entity.Matiere;
import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Repository.EnseignantRepository;
import iset.bizerte.elearning.Repository.MatiereRepository;
import iset.bizerte.elearning.Repository.NiveauRepository;
import iset.bizerte.elearning.Service.MatiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class MatiereServiceImpl implements MatiereService {

    private final MatiereRepository matiereRepository;
    private final EnseignantRepository enseignantRepository;


    @Override
    public List<MatiereDto> findAll() {
        return matiereRepository.findAll().stream()
                .map(MatiereDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MatiereDto findById(Long id) {
        Optional<Matiere> matiere = matiereRepository.findById(id);
        if (matiere.isPresent()) {
            return MatiereDto.FromEntity(matiere.get());



        } else {
            throw new RuntimeException("Matiere not found");
        }


    }

    @Override
    public MatiereDto save(MatiereDto request) {

        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(request.getIdensegnant());
        optionalEnseignant.orElseThrow(() -> new RuntimeException("Cours not found with id: " + request.getIdensegnant()));

        Matiere matiere=MatiereDto.toEntity(request);
        matiere.setEnseignant(optionalEnseignant.get());
        Matiere matiereSaved=matiereRepository.save(matiere);
        return MatiereDto.FromEntity(matiereSaved);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Matiere> matiere = matiereRepository.findById(id);
        if (matiere.isPresent()) {
            matiereRepository.deleteById(id);



        } else {
            throw new RuntimeException("Niveau not found");
        }

    }

    @Override
    public List<MatiereDto> findbyobjet(String kye) {
        return matiereRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(MatiereDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatiereDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public List<MatiereDto> listMatiereByEnsegnantId(Long id) {
        return matiereRepository.listMatiereByEnsegnantId(id)
                .stream()
                .map(MatiereDto::FromEntity)
                .collect(Collectors.toList());
    }


}
