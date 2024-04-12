package mattiaconsiglio.u5w2d5.controllers;

import mattiaconsiglio.u5w2d5.dtos.EmployeeDTO;
import mattiaconsiglio.u5w2d5.entities.Employee;
import mattiaconsiglio.u5w2d5.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated EmployeeDTO employeeDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "username") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable UUID id) {
        return employeeService.getEmployee(id);
    }


    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody @Validated EmployeeDTO employeeDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }
}
