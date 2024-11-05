package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query("SELECT m FROM Seance  m WHERE m.titre LIKE %?1%")
    List<Seance> searchByObjetStartsWith(String kye);



}
