package iset.bizerte.elearning.Repository;

import iset.bizerte.elearning.Entity.Niveau;
import iset.bizerte.elearning.Entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier , Long> {

    @Query("SELECT m FROM Panier  m WHERE m.nom LIKE %?1%")
    List<Panier> searchByObjetStartsWith(String kye);



}
