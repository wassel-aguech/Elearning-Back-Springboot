package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Cours;
import iset.bizerte.elearning.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {




    @Query("SELECT m FROM Cours m WHERE m.titre LIKE %?1%")
    List<Cours> searchByObjetStartsWith(String kye);


    @Query("SELECT c FROM Cours c WHERE c.matieres.id = :id_matiere AND c.niveau.id = :id_niveau")
    List<Cours> findByMatiereIdAndNiveauId(@Param("id_matiere") Long idMatiere, @Param("id_niveau") Long idNiveau);




}
