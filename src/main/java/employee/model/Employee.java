package employee.model;

public class Employee {

    private String employeeName;
    private int employeeSalary;
    private EmployeeCategory employeeCategory;

    public Employee(String employeeName, int employeeSalary, EmployeeCategory employeeCategory) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeCategory = employeeCategory;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

}
