package iset.bizerte.elearning.Service.IMPL;


import iset.bizerte.elearning.Dto.SectionDto;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Repository.CoursRepository;
import iset.bizerte.elearning.Repository.SectionRepository;
import iset.bizerte.elearning.Service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SectionServiceImpl implements SectionService{

    private final SectionRepository sectionRepository;
    private final CoursRepository coursRepository;


    @Override
    public List<SectionDto> findAll() {
        return sectionRepository.findAll().stream()
                .map(SectionDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SectionDto findById(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            return SectionDto.FromEntity(section.get());


        } else {
            throw new RuntimeException("section not found");
        }
    }

    @Override
    public SectionDto save(SectionDto request) {
        Section section= SectionDto.toEntity(request);
        Optional<Cours> optionalCours = coursRepository.findById(request.getIdcours());
        if(optionalCours.isPresent()){
            section.setCours(optionalCours.get());
            Section sectionSaved=sectionRepository.save(section);
            return SectionDto.FromEntity(sectionSaved);
        }
        else {
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            sectionRepository.deleteById(id);



        } else {
            throw new RuntimeException("Section not found");
        }

    }

    @Override
    public List<SectionDto> findbyobjet(String kye) {
        return sectionRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(SectionDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SectionDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public SectionDto uppdate(SectionDto request) {
        return null;
    }
}
