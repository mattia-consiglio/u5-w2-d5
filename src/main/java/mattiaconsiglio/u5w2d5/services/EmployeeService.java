package mattiaconsiglio.u5w2d5.services;

import mattiaconsiglio.u5w2d5.dtos.EmployeeDTO;
import mattiaconsiglio.u5w2d5.entities.Employee;
import mattiaconsiglio.u5w2d5.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d5.exceptions.RecordNotFoundException;
import mattiaconsiglio.u5w2d5.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByUsernameAndEmail(employeeDTO.username(), employeeDTO.email())) {
            throw new BadRequestException("Username and email already in use");
        } else if (employeeRepository.existsByUsername(employeeDTO.username())) {
            throw new BadRequestException("Username already in use");
        } else if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new BadRequestException("Email already in use");
        }
        String photoUrl = "https://ui-avatars.com/api/?name=" + employeeDTO.firstName().charAt(0) + "+" + employeeDTO.lastName().charAt(0);
        return employeeRepository.save(new Employee(employeeDTO.username(), employeeDTO.firstName(), employeeDTO.lastName(), employeeDTO.email(), photoUrl));
    }


    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }


    public Employee getEmployee(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Employee", id));
    }


    public Employee updateEmployee(UUID id, EmployeeDTO employeeDTO) {
        Employee employee = this.getEmployee(id);
        if (employeeRepository.existsByUsernameAndEmail(employeeDTO.username(), employeeDTO.email())) {
            throw new BadRequestException("Username and email already in use");
        } else if (employeeRepository.existsByUsername(employeeDTO.username())) {
            throw new BadRequestException("Username already in use");
        } else if (employeeRepository.existsByEmail(employeeDTO.email())) {
            throw new BadRequestException("Email already in use");
        }
        employee.setUsername(employeeDTO.username());
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastName(employeeDTO.lastName());
        employee.setEmail(employeeDTO.email());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(UUID id) {
        Employee employee = this.getEmployee(id);
        employeeRepository.delete(employee);
    }
}
