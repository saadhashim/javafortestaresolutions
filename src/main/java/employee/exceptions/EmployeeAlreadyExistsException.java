package employee.exceptions;

public class EmployeeAlreadyExistsException extends Exception {
    public EmployeeAlreadyExistsException(String employeeName){
        super("Employee: " +employeeName+ " already exists");
    }
}
