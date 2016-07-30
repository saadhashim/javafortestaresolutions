package employee.impl;

import employee.EmployeeService;
import employee.exceptions.EmployeeAlreadyExistsException;
import employee.model.Employee;

import java.util.ArrayList;

public class EmployeeApplicaton implements EmployeeService {

    private JsonHelper jsonHelper;
    private EmployeeValidator employeeValidator;

    public EmployeeApplicaton() {
        jsonHelper = new JsonHelper();
        employeeValidator = new EmployeeValidator();
    }

    @Override public void addNewEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        employeeValidator.validateEmployee(employee);
        jsonHelper.addEmployee(employee);
    }

    @Override public void removeEmployee(Employee employee) {
        jsonHelper.removeEmployee(employee);
    }

    @Override public Employee getEmployee(String employeeName) {
        return jsonHelper.getEmployee(employeeName);
    }

    @Override public ArrayList<Employee> getAllEmployees() {
        return jsonHelper.getAllEmployees();
    }

}