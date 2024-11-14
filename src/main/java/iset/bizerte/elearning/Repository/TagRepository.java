package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Section;
import iset.bizerte.elearning.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {



    @Query("SELECT m FROM Tag m WHERE m.libelle LIKE %?1%")
    List<Tag> searchByObjetStartsWith(String kye);
}
