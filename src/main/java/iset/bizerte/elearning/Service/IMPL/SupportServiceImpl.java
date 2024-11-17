package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.SupportDto;
import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Seance;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Support;
import iset.bizerte.elearning.Repository.SeanceRepository;
import iset.bizerte.elearning.Repository.SupportRepository;
import iset.bizerte.elearning.Service.SupportService;
import iset.bizerte.elearning.configimageandfile.ImageStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SupportServiceImpl  implements SupportService {

    private final SupportRepository supportRepository;
    private final SeanceRepository seanceRepository;
    private final ImageStorage imageStorage;





    @Override
    public List<SupportDto> findAll() {
        return supportRepository.findAll().stream()
                .map(SupportDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SupportDto findById(Long id) {
        Optional<Support> support = supportRepository.findById(id);
        if (support.isPresent()) {
            return SupportDto.FromEntity(support.get());
        } else {
            throw new RuntimeException("section not found");
        }
    }

    @Override
    public SupportDto save(SupportDto request) {

        Support support= SupportDto.toEntity(request);
        Optional<Seance> optionalSupport = seanceRepository.findById(request.getIdseance());
        if(optionalSupport.isPresent()){
            support.setSeance(optionalSupport.get());
            Support supportSaved=supportRepository.save(support);
            return SupportDto.FromEntity(supportSaved);
        }
        else {
            throw new RuntimeException("not found");
        }

    }

    @Override
    public void deleteById(Long id) {

        Optional<Support> support = supportRepository.findById(id);
        if (support.isPresent()) {
            supportRepository.deleteById(id);
        } else {
            throw new RuntimeException("Section not found");
        }

    }

    @Override
    public List<SupportDto> findbyobjet(String kye) {
        return supportRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(SupportDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupportDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public SupportDto uppdate(SupportDto request) {
        return null;
    }



    public ResponseEntity<Support> findbyId(Long id) {
        if (id == null) {
            //  log.error("student ID is null");
            return null;
        }
        return ResponseEntity.ok(supportRepository.findById(id).get());

    }




    @Override
    public SupportDto uploadsupportfile(Long idsupport, MultipartFile file) {
        ResponseEntity<Support> supportResponse = this.findbyId(idsupport);
        String filepdfName=imageStorage.store(file);

        String filepdfDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/Support/downloadsupport/").path(filepdfName).toUriString();

        Support support = supportResponse.getBody();

        if (support!=null)
            support.setUrlSupport(filepdfDownloadUrl);

        Support supportsaved = supportRepository.save(support);
        return  SupportDto.FromEntity(supportsaved);


    }
}
