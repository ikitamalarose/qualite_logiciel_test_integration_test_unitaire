package sn.supinfo.gestionemployee.service;

import org.springframework.stereotype.Service;
import sn.supinfo.gestionemployee.entity.Departement;
import sn.supinfo.gestionemployee.repository.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{

    private DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository){
        this.departementRepository=departementRepository;
    }
    @Override
    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Optional<Departement> getDepartementById(long id) {
        return departementRepository.findById(id);
    }

    @Override
    public Departement updateDepartement(Departement departement, long id) {
        Departement existingDepartement = departementRepository.findById(id)
                .orElseThrow(()->new RuntimeException());

        existingDepartement.setName(departement.getName());

        departementRepository.save(existingDepartement);
        return existingDepartement;
    }

    @Override
    public void deleteDepartement(long id) {
        departementRepository.deleteById(id);
    }
}
