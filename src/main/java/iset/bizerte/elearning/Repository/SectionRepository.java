package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Matiere;
import iset.bizerte.elearning.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {


    @Query("SELECT m FROM Section m WHERE m.titre LIKE %?1%")
    List<Section> searchByObjetStartsWith(String kye);


}
