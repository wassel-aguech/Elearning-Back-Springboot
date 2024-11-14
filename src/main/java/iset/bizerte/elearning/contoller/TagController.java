package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Dto.TagDto;
import iset.bizerte.elearning.Repository.TagRepository;
import iset.bizerte.elearning.Service.SectionService;
import iset.bizerte.elearning.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;


    @GetMapping("/listall")
    public List<TagDto> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public TagDto findById(@PathVariable("id") Long id) {
        return tagService.findById(id);
    }

    @PostMapping("/save")
    public TagDto save(@RequestBody   TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        tagService.deleteById(id);
    }

    @GetMapping("/search/{kye}")
    public List<TagDto> findbyobjet(@PathVariable("kye")  String kye) {
        return tagService.findbyobjet(kye);
    }
    public List<TagDto> findDate(Date start, Date end) {
        return tagService.findDate(start, end);
    }

    @PostMapping("/update")
    public TagDto uppdate(@RequestBody  TagDto request) {
        return tagService.uppdate(request);
    }

}
