package employee;

import employee.exceptions.EmployeeAlreadyExistsException;
import employee.model.Employee;

import java.util.ArrayList;

public interface EmployeeService {

    void addNewEmployee(Employee employee) throws EmployeeAlreadyExistsException;

    void removeEmployee(Employee employee);

    Employee getEmployee(String employeeName);

    ArrayList<Employee> getAllEmployees();
}
