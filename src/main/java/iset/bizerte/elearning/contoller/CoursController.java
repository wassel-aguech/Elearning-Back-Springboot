package iset.bizerte.elearning.contoller;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Service.CoursService;
import iset.bizerte.elearning.configimageandfile.ImageStorage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;





import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Cours")
@RequiredArgsConstructor
public class CoursController {

    private final CoursService coursService;
    private final ImageStorage imageStorage;


@GetMapping("/addcoursestostudent/{id}")
    public Void addcoursestostudent(@PathVariable("id")  Long id) {
        return coursService.addcoursestostudent(id);
    }

    @GetMapping("/listall")
    public List<CoursDto> findAll() {
        return coursService.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public CoursDto findById(@PathVariable("id") Long id) {
        return coursService.findById(id);
    }

    @PostMapping("/save")
    public CoursDto save(@RequestBody CoursDto request) {
        return coursService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        coursService.deleteById(id);
    }

    @GetMapping("/search/{kye}")
    public List<CoursDto> findbyobjet(@PathVariable("kye") String kye) {
        return coursService.findbyobjet(kye);
    }

    @GetMapping("/finddate")
    public List<CoursDto> findDate( Date start, Date end) {
        return coursService.findDate(start, end);
    }

    @PutMapping("/update")
    public CoursDto update(@RequestBody CoursDto request) {
        return coursService.uppdate(request);
    }



    @GetMapping("/listcoursbyidniveauetidmatiere/{idmatiere}/{idniveau}")
    public List<CoursDto> listcoursbyidniveauetidmatiere(@PathVariable("idmatiere")   Long idmatiere, @PathVariable("idniveau") Long idniveau) {
        return coursService.listcoursbyidniveauetidmatiere(idmatiere, idniveau);
    }

    @PostMapping(path = "/uploadFile/{IdCoursDto}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PostMapping("/uploadFile/{IdCoursDto}")
    public CoursDto uploadImageCoursDto(@PathVariable("IdCoursDto") Long IdCoursDto, @RequestParam MultipartFile image ,@RequestParam   MultipartFile file ) {
        return coursService.uploadcoursfile(IdCoursDto, image , file);
    }

    @GetMapping("/downloadcoursimage/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }

    
    @GetMapping("/downloadcourspdffile/{imageName}")
    public ResponseEntity<Resource> downloadfile(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }


    public CoursDto uploadcoursfile(Long Idcours, MultipartFile image, MultipartFile file) {
        return coursService.uploadcoursfile(Idcours, image, file);
    }
}
