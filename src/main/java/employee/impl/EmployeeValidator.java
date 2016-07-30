package employee.impl;

import employee.model.Employee;

public class EmployeeValidator {

    public void validateEmployee(Employee employee) {
        validateNameLength(employee.getEmployeeName());
        validateSalary(employee.getEmployeeSalary());
    }

    public void validateNameLength(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    public void validateSalary(int salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary is below minimum limit");
        } else if (salary > 100000) {
            throw new IllegalArgumentException("Salary is above maximum limit");
        }
    }
}