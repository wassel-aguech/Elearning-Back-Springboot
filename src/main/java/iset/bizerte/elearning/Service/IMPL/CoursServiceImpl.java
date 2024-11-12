package iset.bizerte.elearning.Service.IMPL;

import iset.bizerte.elearning.Dto.CoursDto;
import iset.bizerte.elearning.Entity.*;
import iset.bizerte.elearning.Repository.*;
import iset.bizerte.elearning.Service.CoursService;
import iset.bizerte.elearning.configimageandfile.ImageStorage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursServiceImpl implements CoursService {


private final CoursRepository coursRepository;
    private final NiveauRepository niveauRepository;
    private final MatiereRepository matiereRepository;
    private final EnseignantRepository enseignantRepository;
    private final TagRepository tagRepository;
    private final SectionRepository sectionRepository;
    private final EtudiantRepository etudiantRepository;
    private final PanierRepository panierRepository;
    private final ImageStorage imageStorage;
    @Override
    public List<CoursDto> findAll() {
        return coursRepository.findAll().stream()
                .map(CoursDto::FromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public CoursDto findById(Long id) {
        Optional<Cours> cours = coursRepository.findById(id);
        if (cours.isPresent()) {
            return CoursDto.FromEntity(cours.get());
        }
        else {
            throw new RuntimeException("Cours not found");
        }
    }

    @Override
    public CoursDto save(CoursDto request) {
        Cours cours = CoursDto.ToEntity(request);
       // Optional<Matiere> matierefound = matiereRepository.findById(request.getIdmatiere());

        Matiere matierefound = matiereRepository.findById(request.getIdmatiere())
                .orElseThrow(() -> new EntityNotFoundException("No matiere found with ID:: " + request.getIdmatiere()));

        Niveau niveaufound = niveauRepository.findById(request.getIdniveau())
                .orElseThrow(() -> new EntityNotFoundException("No niveau found with ID:: " +request.getIdniveau()));


        Enseignant Enseignantfound = enseignantRepository.findById(request.getIdenseignant())
                .orElseThrow(() -> new EntityNotFoundException("No ensegnant found with ID:: " +request.getIdenseignant()));

        //Optional<Niveau> niveaufound = niveauRepository.findById(request.getIdniveau());
       // Optional<Enseignant> Enseignantfound = enseignantRepository.findById(request.getIdenseignant());


       // if (matierefound.isPresent() && niveaufound.isPresent() && Enseignantfound.isPresent()) {
            cours.setMatieres(matierefound);
            cours.setNiveau(niveaufound);
            cours.setTeacher(Enseignantfound);

            List<Tag> tagtoadd;
            if (request.getTagid().isEmpty()) {
                throw new IllegalArgumentException("you need to add a tag ");
            } else {
                tagtoadd = new ArrayList<>();
                for (Long Idmatieres : request.getTagid()) {
                    Optional<Tag> tagfound = tagRepository.findById(Idmatieres);
                    tagfound.ifPresent(tagtoadd::add);
                }

            }
           /* List<Section> sectiontoadd;
            if (request.getSectionid().isEmpty()) {
                throw new IllegalArgumentException("you need to add a section ");
            } else {
                sectiontoadd = new ArrayList<>();
                for (Long Idsection : request.getSectionid()) {
                    Optional<Section> sectionfound = sectionRepository.findById(Idsection);
                    sectionfound.ifPresent(sectiontoadd::add);
                }
            }*/
            System.err.println(tagtoadd);
            cours.setTags(tagtoadd);
            //cours.setSections(sectiontoadd);
            Cours coursSaved = coursRepository.save(cours);
            return CoursDto.FromEntity(coursSaved);


      //  }
      //  else {
      //      throw new RuntimeException("Cours not found");
      //  }


    }

    @Override
    public void deleteById(Long id) {
          Optional<Cours> cours = coursRepository.findById(id);
          if (cours.isPresent()) {
                coursRepository.deleteById(id);
            }
            else {
                throw new RuntimeException("Cours not found");
            }
    }

    @Override
    public List<CoursDto> findbyobjet(String kye) {
        return coursRepository.searchByObjetStartsWith(kye)
                .stream()
                .map(CoursDto::FromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursDto> findDate(Date start, Date end) {
        return List.of();
    }

    @Override
    public CoursDto uppdate(CoursDto request) {
        Optional<Cours> cours = coursRepository.findById(request.getId());
        if (cours.isPresent()) {
            Cours coursconverted = CoursDto.ToEntity(request);
            cours.get().setMatieres(coursconverted.getMatieres());
            cours.get().setTeacher(coursconverted.getTeacher());
            cours.get().setNiveau(coursconverted.getNiveau());
            cours.get().setTags(coursconverted.getTags());
            cours.get().setSections(coursconverted.getSections());
            Cours coursSaved = coursRepository.save(cours.get());
            return CoursDto.FromEntity(coursSaved);
        }
        else {
            throw new RuntimeException("Cours not found");
        }
    }

    @Override
    public Void addcoursestostudent(Long idpanier ) {
        Optional<Panier> panier = panierRepository.findById(idpanier);

        if (panier.isPresent()) {
            // Log the Panier for debugging
            System.err.println(panier.get());

            // Find the Etudiant linked to this Panier
            Optional<Etudiant> etudiantOptional = etudiantRepository.findById(panier.get().getEtudiant().getId());

            // If the Panier contains courses and the Etudiant is found
            if (!panier.get().getCours().isEmpty() && etudiantOptional.isPresent()) {
                Etudiant etudiant = etudiantOptional.get();

                // Create a new set to avoid shared reference issues
                List<Cours> copiedCourses = new ArrayList<>(panier.get().getCours());

                // Update the Etudiant's courses with the new set
                etudiant.setCours(copiedCourses);

                // Save the updated Etudiant
                etudiantRepository.save(etudiant);

                // Return properly after successful operation
                return null;
            } else {
                throw new RuntimeException("Panier is empty or student not found!");
            }
        }throw new RuntimeException("Panier is empty or student not found!");
    }

    @Override
    public List<CoursDto> listcoursbyidniveauetidmatiere(Long idmatiere, Long idniveau) {
        return coursRepository.findByMatiereIdAndNiveauId(idmatiere , idniveau).stream()
                .map(CoursDto::FromEntity)
                .collect(java.util.stream.Collectors.toList());
    }



    public ResponseEntity<Cours> findbyId(Long id) {
        if (id == null) {
            //  log.error("student ID is null");
            return null;
        }
        return ResponseEntity.ok(coursRepository.findById(id).get());

    }



    @Override
    public CoursDto uploadcoursfile(Long Idcours, MultipartFile image, MultipartFile file) {
        ResponseEntity<Cours> coursResponse = this.findbyId(Idcours);
        String imageName=imageStorage.store(image);
        String filepdfName=imageStorage.store(file);

        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/Cours/downloadcoursimage/").path(imageName).toUriString();
        String filepdfDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/Cours/downloadcourspdffile/").path(filepdfName).toUriString();

        Cours cours = coursResponse.getBody();

        if (cours!=null)
            cours.setUrlimage(fileImageDownloadUrl);
        cours.setUrlcours(filepdfDownloadUrl);

        Cours courssaved = coursRepository.save(cours);
        return  CoursDto.FromEntity(courssaved);



    }



   /* @Override
    public CoursDto uploadcoursfile(Long IdCours, MultipartFile image) {
        ResponseEntity<Cours> coursResponse = this.findbyId(IdCours);
        String imageName=imageStorage.store(image);
        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("api/cours/downloadcoursFile/").path(imageName).toUriString();
        Cours cours = coursResponse.getBody();

        if (cours!=null)
            cours.setFilecours(fileImageDownloadUrl);
        Cours courssaved = coursRepository.save(cours);
        return  CoursDto.fromEntity(courssaved);*/

    }









