package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.SectionDto;
import iset.bizerte.elearning.Service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Section")
@RequiredArgsConstructor

public class SectionController {

    private final SectionService sectionService;


    @GetMapping("/listall")
    public List<SectionDto> findAll() {
        return sectionService.findAll();
    }
    @GetMapping("/getbyid/{id}")
    public SectionDto findById(@PathVariable("id") Long id) {
        return sectionService.findById(id);
    }
    @PostMapping("/save")
    public SectionDto save(@RequestBody   SectionDto request) {
        return sectionService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        sectionService.deleteById(id);
    }
    @GetMapping("/search/{kye}")
    public List<SectionDto> findbyobjet(@PathVariable("kye")  String kye) {
        return sectionService.findbyobjet(kye);
    }

    public List<SectionDto> findDate(Date start, Date end) {
        return sectionService.findDate(start, end);
    }
    @PostMapping("/update")
    public SectionDto uppdate(@RequestBody  SectionDto request) {
        return sectionService.uppdate(request);
    }
}
