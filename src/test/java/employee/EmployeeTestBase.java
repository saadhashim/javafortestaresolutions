package employee;


import com.google.common.truth.Truth;
import employee.model.Employee;
import employee.model.EmployeeCategory;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by Rickard on 2016-06-19.
 */
public class EmployeeTestBase {
    public static int MINIMUM_SALARY = 1;
    public static int MAXIMUM_SALARY = 100000;

    public Employee getTestEmployee() {
        return new Employee(getNewEmployeeName(), MINIMUM_SALARY, EmployeeCategory.DEVELOPER);
    }

    private String getNewEmployeeName() {
        return RandomStringUtils.randomAlphabetic(15);
    }

    public static void verifyCreatedEmployee(Employee expectedEmployee, Employee createdEmployee) {
        Truth.assertWithMessage("employeeName differs")
                .that(createdEmployee.getEmployeeName())
                .isEqualTo(expectedEmployee.getEmployeeName());

        Truth.assertWithMessage("employeeSalary differs")
                .that(createdEmployee.getEmployeeSalary())
                .isEqualTo(expectedEmployee.getEmployeeSalary());

        Truth.assertWithMessage("employeeCategory differs")
                .that(createdEmployee.getEmployeeCategory())
                .isEqualTo(expectedEmployee.getEmployeeCategory());
    }
}
