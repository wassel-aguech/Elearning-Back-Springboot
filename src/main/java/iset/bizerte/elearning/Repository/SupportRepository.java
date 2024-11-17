package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportRepository extends JpaRepository<Support, Long> {



    @Query("SELECT m FROM Support m WHERE m.name LIKE %?1%")
    List<Support> searchByObjetStartsWith(String kye);
}
