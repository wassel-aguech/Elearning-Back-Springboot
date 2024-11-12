package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.TagDto;
import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Tag;
import iset.bizerte.elearning.Repository.CoursRepository;
import iset.bizerte.elearning.Repository.TagRepository;
import iset.bizerte.elearning.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TageServiceImpl implements TagService {


    private final TagRepository tagRepository;
    private final CoursRepository coursRepository;



    @Override
    public List<TagDto> findAll() {
        return tagRepository.findAll().stream()
                .map(TagDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            return TagDto.FromEntity(tag.get());
        } else {
            throw new RuntimeException("section not found");
        }
    }

    @Override
    public TagDto save(TagDto request) {
        Tag tag= TagDto.toEntity(request);
        Optional<Cours> optionalCours = coursRepository.findById(request.getIdcours());
        if(optionalCours.isPresent()){
            tag.setCours(optionalCours.get());
            Tag tagSaved=tagRepository.save(tag);
            return TagDto.FromEntity(tagSaved);
        }
        else {
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void deleteById(Long id) {

        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            tagRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tag not found");
        }

    }

    @Override
    public List<TagDto> findbyobjet(String kye) {
      /*  return tagRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(TagDto::FromEntity)
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public List<TagDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public TagDto uppdate(TagDto request) {
        return null;
    }
}
