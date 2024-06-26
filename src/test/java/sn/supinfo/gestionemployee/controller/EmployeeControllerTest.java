package sn.supinfo.gestionemployee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import sn.supinfo.gestionemployee.entity.Departement;
import sn.supinfo.gestionemployee.entity.Employee;
import sn.supinfo.gestionemployee.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;


    Employee employee;

    Departement departement;


    @BeforeEach
    public void setup(){
        departement=Departement.builder()
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

    //Post Controller
    @Test
    @Order(1)
    @Rollback(value = false)
    void createEmployee() throws Exception{

        // precondition
        given(employeeService.saveEmployee(any(Employee.class))).willReturn(employee);

        // action     // Action : effectuer une requête POST vers /employees avec l'employé sérialisé en JSON
        ResultActions response = mockMvc.perform(post("/employees/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // Vérification : vérifier le statut et le contenu de la réponse JSON
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname",
                        is(employee.getFirstname())))
                .andExpect(jsonPath("$.name",
                        is(employee.getName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())))
                .andExpect(jsonPath("$.departement.name",
                        is(employee.getDepartement().getName())));
    }

    //Get Controller
    @Test
    @Order(4)
    void getAllEmployees() throws Exception{

        // precondition
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        employeesList.add(Employee.builder().id(2L).firstname("Akorede").name("KABIR").email("akoredekabir@gmail.com").departement(departement).build());
        given(employeeService.getAllEmployees()).willReturn(employeesList);

        // action
        ResultActions response = mockMvc.perform(get("/employees"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(employeesList.size())));
    }

    //get by id controlle
    @Test
    @Order(2)
    void getEmployeeById() throws Exception{

        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));

        // action
        ResultActions response = mockMvc.perform(get("/employees/{id}", employee.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstname", is(employee.getFirstname())))
                .andExpect(jsonPath("$.name", is(employee.getName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())))
                .andExpect(jsonPath("$.departement.name", is(employee.getDepartement().getName())));
    }

    //Update employee
    @Test
    @Order(3)
    void updateEmployee() throws Exception{

        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));
        employee.setFirstname("Kinata");
        employee.setEmail("kinatacome@gmail.com");
        given(employeeService.updateEmployee(employee,employee.getId())).willReturn(employee);

        // action
        ResultActions response = mockMvc.perform(put("/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstname", is(employee.getFirstname())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    // delete employee
    @Test
    void deleteEmployee() throws Exception{

        // precondition
        willDoNothing().given(employeeService).deleteEmployee(employee.getId());

        // action
        ResultActions response = mockMvc.perform(delete("/employees/{id}", employee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}