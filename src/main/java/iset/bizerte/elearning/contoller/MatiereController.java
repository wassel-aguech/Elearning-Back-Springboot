package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.MatiereDto;
import iset.bizerte.elearning.Service.MatiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Matiere")
@RequiredArgsConstructor

public class MatiereController {


    private final MatiereService matiereService;

    @GetMapping("/listall")
    public List<MatiereDto> findAll() {
        return matiereService.findAll();
    }
    @GetMapping("/getbyid/{id}")
    public MatiereDto findById(@PathVariable("id")  Long id) {
        return matiereService.findById(id);
    }
    @PostMapping("/save")
    public MatiereDto save(@RequestBody   MatiereDto request) {
        return matiereService.save(request);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")    Long id) {
        matiereService.deleteById(id);
    }

    @GetMapping("/search/{kye}")
    public List<MatiereDto> findbyobjet(@PathVariable("kye")   String kye) {
        return matiereService.findbyobjet(kye);
    }

    public List<MatiereDto> findDate(Date start, Date end) {
        return matiereService.findDate(start, end);
    }
}
