package sn.supinfo.gestionemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.supinfo.gestionemployee.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);
}
