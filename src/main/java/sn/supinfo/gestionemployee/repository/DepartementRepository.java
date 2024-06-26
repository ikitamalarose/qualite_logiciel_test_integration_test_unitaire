package sn.supinfo.gestionemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.supinfo.gestionemployee.entity.Departement;

import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
    Optional<Departement> findByName(String name);
}
