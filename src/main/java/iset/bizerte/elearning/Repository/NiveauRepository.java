package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Dto.NiveauDto;
import iset.bizerte.elearning.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NiveauRepository extends JpaRepository<Niveau, Long> {




    @Query("SELECT m FROM Niveau  m WHERE m.niveaustudent LIKE %?1%")
    List<Niveau> searchByObjetStartsWith(String kye);



}
