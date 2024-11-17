package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.SupportDto;
import iset.bizerte.elearning.Service.SupportService;
import iset.bizerte.elearning.configimageandfile.ImageStorage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;
    private final ImageStorage imageStorage;





    @GetMapping("/listall")
    public List<SupportDto> findAll() {
        return supportService.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public SupportDto findById( @PathVariable("id") Long id) {
        return supportService.findById(id);
    }


    @PostMapping("/save")
    public SupportDto save(@RequestBody  SupportDto request) {
        return supportService.save(request);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        supportService.deleteById(id);
    }


    @GetMapping("/search/{kye}")
    public List<SupportDto> findbyobjet(@PathVariable String kye) {
        return supportService.findbyobjet(kye);
    }

    @GetMapping("/findByDate")
    public List<SupportDto> findDate(Date start, Date end) {
        return supportService.findDate(start, end);
    }


    @PostMapping("/update")
    public SupportDto uppdate(@RequestBody SupportDto request) {
        return supportService.uppdate(request);
    }


    @PostMapping(path = "/uploadFile/{idsupport}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SupportDto uploadsupportfile(@PathVariable Long idsupport,@RequestParam MultipartFile file) {
        return supportService.uploadsupportfile(idsupport, file);
    }


    @GetMapping("/downloadsupport/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }
}
