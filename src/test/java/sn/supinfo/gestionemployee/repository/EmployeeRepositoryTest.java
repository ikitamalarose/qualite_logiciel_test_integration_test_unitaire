package sn.supinfo.gestionemployee.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import sn.supinfo.gestionemployee.entity.Departement;
import sn.supinfo.gestionemployee.entity.Employee;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private  DepartementRepository departementRepository;


    @Test
    @DisplayName("Test 1 : Save Employee")
    @Order(1)/*est une annotation de Spring utilisée pour spécifier l'ordre dans lequel les méthodes de test doivent être exécutées.*/
    @Rollback(value = false)/*signifie que la transaction ne sera pas annulée après l'exécution du test, ce qui permet de conserver les modifications apportées à la base de données.*/
    public  void  saveEmployeeTest () {

        // Créez et sauvegardez un département factice
        Departement departement = Departement.builder()
                .name("IT")
                .build();
        departement = departementRepository.save(departement);

        //Action
        Employee employee  = Employee.builder()
                .firstname( "Jeremy" )
                .name( "OBIANG" )
                .email( "jeremyobiang@gmail.com" )
                .departement(departement)
                .build();

        employeeRepository.save(employee);

        System.out.println("Saved employee ID: " + employee.getId());
        Assertions.assertThat(employeeRepository.findById(employee.getId())).isPresent();

        //Vérifiez
        /*
        * S'assurer que l'employé a été correctement sauvegardé dans la base de données
        * et qu'un identifiant valide a été généré et assigné.*/
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isNotNull();
        Assertions.assertThat(employee.getId()).isGreaterThan( 0 );
    }

    @Test
    @DisplayName("Test 2 : Recuperation d'un Employee")
    @Order(2)
    public  void  getEmployeeTest () {

        //Action
        Employee  employee  = employeeRepository.findById( 1L ).get();
        //Vérifiez
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo( 1L );
    }

    @Test
    @DisplayName("Test 3 : Recuperation de la liste des Employees")
    @Order(3)
    public  void  getListOfEmployeesTest () {

        //Action
        List<Employee> employees = employeeRepository.findAll();
        //Verify
        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test X : Find User By Email")
    @Order(4)
    void findByEmail() {
        // Recherchez l'employé par email
        Employee foundEmployee = employeeRepository.findByEmail("jeremyobiang@gmail.com").orElse(null);

        System.out.println(foundEmployee);
        // Vérifiez
        Assertions.assertThat(foundEmployee).isNotNull();
        Assertions.assertThat(foundEmployee.getEmail()).isEqualTo("jeremyobiang@gmail.com");
    }

    @Test
    @DisplayName("Test 4 : Update Employee")
    @Order(5)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        //Action
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmail("jeremy2obiang@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);

        //Verify
        System.out.println(employeeUpdated);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("jeremy2obiang@gmail.com");

    }

    @Test
    @DisplayName("Test 5 : Delete Employee")
    @Order(6)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        //Action
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);

        //Verify
        Assertions.assertThat(employeeOptional).isEmpty();
    }
}