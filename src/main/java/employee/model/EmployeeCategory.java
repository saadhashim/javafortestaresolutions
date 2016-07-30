package employee.model;

public enum EmployeeCategory {
    DEVELOPER, TEST_AUTOMATOR, ARCHITECT, CIO, CEO;

    public static EmployeeCategory toEnum(String str) {
        for (EmployeeCategory employeeCategory : EmployeeCategory.values()) {
            if (employeeCategory.toString().equals(str)) {
                return employeeCategory;
            }
        }
        return null;
    }
}
