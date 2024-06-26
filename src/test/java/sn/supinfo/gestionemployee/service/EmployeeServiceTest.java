package sn.supinfo.gestionemployee.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sn.supinfo.gestionemployee.entity.Departement;
import sn.supinfo.gestionemployee.entity.Employee;
import sn.supinfo.gestionemployee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    private Departement departement;


    @BeforeEach
    public void setup(){

        departement= Departement.builder()
                .id(1L)
                .name("RESEAU")
                .build();

        employee = Employee.builder()
                .id(1L)
                .firstname("Junior")
                .name("Come")
                .email("juniorcome@gmail.com")
                .departement(departement)
                .build();

        // Vérifiez que l'employé est correctement sauvegardé avec son ID généré
        System.out.println("Saved employee ID: " + employee.getId());
        Assertions.assertThat(employee.getId()).isNotNull();

    }

    @Test
    @Order(1)
    void saveEmployee() {

        // precondition
        given(employeeRepository.save(employee)).willReturn(employee);

        //action
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // verify the output
        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @Order(4)
    void getAllEmployees() {

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstname("Akorede")
                .name("KABIR")
                .email("akoredekabir@gmail.com")
                .departement(departement)
                .build();

        // precondition
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // action
        List<Employee> employeeList = employeeService.getAllEmployees();

        // verify
        System.out.println(employeeList);
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isGreaterThan(1);
    }

    @Test
    @Order(2)
    void getEmployeeById() {

        // precondition
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // action
        Employee existingEmployee = employeeService.getEmployeeById(employee.getId()).get();

        // verify
        System.out.println(existingEmployee);
        assertThat(existingEmployee).isNotNull();
    }

    @Test
    @Order(3)
    void updateEmployee() {

        // precondition
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        employee.setFirstname("Kinata");
        employee.setEmail("kinatacome@gmail.com");
        given(employeeRepository.save(employee)).willReturn(employee);

        // action
        Employee updatedEmployee = employeeService.updateEmployee(employee,employee.getId());

        // verify
        System.out.println(updatedEmployee);
        assertThat(updatedEmployee.getEmail()).isEqualTo("kinatacome@gmail.com");
        assertThat(updatedEmployee.getFirstname()).isEqualTo("Kinata");
    }

    @Test
    void deleteEmployee() {

        // precondition
        willDoNothing().given(employeeRepository).deleteById(employee.getId());

        // action
        employeeService.deleteEmployee(employee.getId());

        // verify
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }
}