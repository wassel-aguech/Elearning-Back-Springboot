package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.PanierDto;
import iset.bizerte.elearning.Service.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Panier")
@RequiredArgsConstructor

public class PanierController {

    private final PanierService panierService;
    @GetMapping("/listall")
    public List<PanierDto> findAll() {
        return panierService.findAll();
    }
    @GetMapping("/getbyid/{id}")
    public PanierDto findById(@PathVariable("id")  Long id) {
        return panierService.findById(id);
    }
    @PostMapping("/save")
    public PanierDto save(@RequestBody     PanierDto request) {
        return panierService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")  Long id) {
        panierService.deleteById(id);
    }

    @GetMapping("/search/{kye}")
    public List<PanierDto> findbyobjet(@PathVariable("kye")   String kye) {
        return panierService.findbyobjet(kye);
    }

    public List<PanierDto> findDate(Date start, Date end) {
        return panierService.findDate(start, end);
    }
}
