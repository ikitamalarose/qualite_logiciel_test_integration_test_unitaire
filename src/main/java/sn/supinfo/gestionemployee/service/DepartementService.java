package sn.supinfo.gestionemployee.service;

import sn.supinfo.gestionemployee.entity.Departement;

import java.util.List;
import java.util.Optional;

public interface DepartementService {

    Departement saveDepartement(Departement departement);
    List<Departement> getAllDepartements();
    Optional<Departement> getDepartementById(long id);
    Departement updateDepartement(Departement departement,long id);
    void deleteDepartement(long id);
}
