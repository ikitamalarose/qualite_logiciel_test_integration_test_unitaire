package sn.supinfo.gestionemployee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.supinfo.gestionemployee.entity.Departement;

import sn.supinfo.gestionemployee.service.DepartementService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/departements")
@RestController
public class DepartementController {
    private DepartementService departementService;

    public DepartementController(DepartementService departementService){
        this.departementService=departementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departement createDepartement(@RequestBody Departement departement){
        return departementService.saveDepartement(departement);
    }

    @GetMapping
    public List<Departement> getAllDepartements(){
        return departementService.getAllDepartements();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Departement>> getDepartementById(@PathVariable("id") long id){
        return new ResponseEntity<Optional<Departement>>(departementService.getDepartementById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable("id") long id,
                                                   @RequestBody Departement departement){
        return new ResponseEntity<Departement>(departementService.updateDepartement(departement,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable("id") long id){
        departementService.deleteDepartement(id);
        return new ResponseEntity<String>("Departement deleted successfully!.", HttpStatus.OK);

    }
}
