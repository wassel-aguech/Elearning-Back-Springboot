package iset.bizerte.elearning.Service.IMPL;


import iset.bizerte.elearning.Dto.SeanceDto;

import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Seance;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Repository.SeanceRepository;
import iset.bizerte.elearning.Repository.SectionRepository;
import iset.bizerte.elearning.Service.SeanceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SeanceServiceImpl implements SeanceService {

private final SeanceRepository seanceRepository;
private final SectionRepository sectionRepository;
    @Override
    public List<SeanceDto> findAll() {
        return seanceRepository.findAll().stream()
                .map(SeanceDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SeanceDto findById(Long id) {
        Optional<Seance> seance = seanceRepository.findById(id);
        if (seance.isPresent()) {
            return SeanceDto.FromEntity(seance.get());


        } else {
            throw new RuntimeException("seance not found");
        }

    }

    @Override
    public SeanceDto save(SeanceDto request) {
        Seance seance= SeanceDto.toEntity(request);
        Optional<Section> optionalSection = sectionRepository.findById(request.getIdsection());
        if(optionalSection.isPresent()){
            seance.setSection(optionalSection.get());
            Seance seanceSaved=seanceRepository.save(seance);
            return SeanceDto.FromEntity(seanceSaved);
        }
        else {
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Seance> seance = seanceRepository.findById(id);
        if (seance.isPresent()) {
            seanceRepository.deleteById(id);


        } else {
            throw new RuntimeException("seance not found");
        }

    }

    @Override
    public List<SeanceDto> findbyobjet(String kye) {
        return seanceRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(SeanceDto::FromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public List<SeanceDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public SeanceDto uppdate(SeanceDto request) {
        return null;
    }
}
