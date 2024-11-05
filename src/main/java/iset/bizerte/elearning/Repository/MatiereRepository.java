package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Matiere;
import iset.bizerte.elearning.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {




    @Query("SELECT m FROM Matiere  m WHERE m.libelle LIKE %?1%")
    List<Matiere> searchByObjetStartsWith(String kye);

}
