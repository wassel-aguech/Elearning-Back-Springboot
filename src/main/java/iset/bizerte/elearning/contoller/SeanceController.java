package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.SeanceDto;
import iset.bizerte.elearning.Service.SeanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Seance")
@RequiredArgsConstructor

public class SeanceController {

    private final SeanceService seanceService;







    @GetMapping("/listall")
    public List<SeanceDto> findAll() {
        return seanceService.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public SeanceDto findById(@PathVariable("id") Long id) {
        return seanceService.findById(id);
    }
    @PostMapping("/save")
    public SeanceDto save(@RequestBody SeanceDto request) {
        return seanceService.save(request);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")  Long id) {
        seanceService.deleteById(id);
    }
    @GetMapping("/search/{kye}")
    public List<SeanceDto> findbyobjet(@PathVariable("kye") String kye) {
        return seanceService.findbyobjet(kye);
    }

    public List<SeanceDto> findDate(Date start, Date end) {
        return seanceService.findDate(start, end);
    }
    @PostMapping("/update")
    public SeanceDto uppdate(@RequestBody  SeanceDto request) {
        return seanceService.uppdate(request);
    }
}
