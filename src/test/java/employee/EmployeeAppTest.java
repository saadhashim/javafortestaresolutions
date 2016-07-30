package employee;

import com.google.common.truth.Truth;
import employee.exceptions.EmployeeAlreadyExistsException;
import employee.impl.EmployeeApplicaton;
import employee.model.Employee;
import employee.model.EmployeeCategory;
import org.junit.Test;

import java.util.ArrayList;

public class EmployeeAppTest extends EmployeeTestBase {
    /**
     * När en Java-klass (EmployeeApplication) implementerar ett interface (employee)
     * kan du använda en instans av den klass som en instans av gränssnittet.
     *
     * Lägg märke till hur variabeln (employeeApp) förklaras vara av gränssnittet
     * typ EmployeeApp medan objektet som skapas är av typen EmployeeApplicaton.
     * Java tillåter detta eftersom klassen EmployeeApplicaton implementerar
     * EmployeeApp gränssnittet (interfacet). Du kan sedan referera instanser
     * av EmployeeApplicaton-klassen som instanser av EmployeeApp-gränssnittet.
     *
     * Du kan inte skapa instanser av ett Java-gränssnitt av sig själv. Du måste
     * alltid skapa en instans av en viss klass som implementerar gränssnittet,
     * och referera till instansen som en instans av gränssnittet.
     */
    EmployeeService employeeService = new EmployeeApplicaton();

    /**
     * @keywords objekt, variabler
     * Vi testar att det går att skapa en ny anställd.
     *
     * Först anropar vi metoden addNewEmployee för att skapa en ny anställd.
     * Därefter anropar vi metoden getEmployee för att kunna verifera att kunden skapats.
     */
    @Test
    public void addNewEmployee() throws Exception {
        //Testdata (förväntat resultat) sparas till variabeln newEmployee
        Employee newEmployee = getTestEmployee();

        //Anställd skapas med testdatat
        employeeService.addNewEmployee(newEmployee);

        //Vi hämtar den anställde som skapats (faktiskt resultat) och sparar denne till variabeln addedEmployee
        Employee addedEmployee = employeeService.getEmployee(newEmployee.getEmployeeName());

        /**
         * Här jämför vi den anställde vi försökt skapa (newEmployee, förväntat resultat)
         * med den anställde som faktiskt skapades (addedEmployee, faktiskt resultat).
         * Nedan verifierar namn, lön och anställningskategori.
         */
        Truth.assertWithMessage("employeeName differs")
                .that(addedEmployee.getEmployeeName())
                .isEqualTo(newEmployee.getEmployeeName());

        Truth.assertWithMessage("employeeSalary differs")
                .that(addedEmployee.getEmployeeSalary())
                .isEqualTo(newEmployee.getEmployeeSalary());

        Truth.assertWithMessage("employeeCategory differs")
                .that(addedEmployee.getEmployeeCategory())
                .isEqualTo(newEmployee.getEmployeeCategory());
    }

    /**
     * @keywords JUnit expected
     * Vi testar att det inte går att skapa samma kund två gånger.
     *
     * Vi anropar addNewEmployee med samma kund två gånger.
     * I expected-parametern talar vi om att vi förväntar oss
     * att testet ska kasta EmployeeAlreadyExistsException
     */
    @Test (expected = EmployeeAlreadyExistsException.class)
    public void verifyEmployeeAlreadyExists() throws Exception {
        Employee newEmployee = getTestEmployee();
        employeeService.addNewEmployee(newEmployee);
        employeeService.addNewEmployee(newEmployee);
    }

    /**
     * @keywords Extract method
     * Vi testar precis samma sak som ovan, men med mer kompakt kod.
     *
     * Du kanske undrar varför vi i förra testet inte jämförde objektet newEmployee
     * med addedEmployee i en verifieringspunkt?
     *
     * Om du provar att göra detta kommer du få ett fel i stil med:
     * Not true that <employee.Employee@6a2f6f80> is equal to <employee.Employee@45b4c3a9>
     * .isEqualTo kollar i detta fal om minnesadressen för de två objekten (newEmployee, addedEmployee)
     * är likvärdiga. Vilket de aldrig kommer vara.
     *
     * Det vi kan göra är att bryta ut verifieringspunkterna i en egen metod. Om vi vet att
     * dessa verifieringar är något som kommer behöva göras fler gånger än en så kan vi alltså
     * bryta ut dem till en generell metod.
     */
    @Test
    public void addNewEmployeeOverride() throws Exception {
        Employee newEmployee = getTestEmployee();
        employeeService.addNewEmployee(newEmployee);

        Employee addedEmployee = employeeService.getEmployee(newEmployee.getEmployeeName());

        verifyCreatedEmployee(newEmployee, addedEmployee);
    }

    /**
     * @keywords ArrayList
     * Vi testar att det går att hämta en befintlig anställd.
     *
     * Vi gör detta genom att hämta upp en lista med alla anställda (getAllEmployees).
     * Vi plockar därefter ut en anställd från listan och anropar getEmployee med denne.
     *
     * Metoden getAllEmployees returnerar en lista (flera värden/objekt) med Employees.
     * I detta fal en ArrayList. En ArrayList är bra om man inte vet hur många värden
     * som kommer finnas i sin lista. Som testare är det bra att veta hur man plockar
     * ut data från en ArrayList. I detta fall har vi inte några speciella krav på datat
     * så vi hämtar ut det första värdet från listan.
     */
    @Test
    public void getExistingEmployee() {
        //Datat som returneras av getAllEmployees() sparas i en variabel som heter allEmployees
        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();

        //Vi hämtar ut det första värdet från listan
        Employee employeeFromList = allEmployees.get(0);

        //Vi anropar metoden getEmployee med den anställde vi plockade ut från listan
        Employee employee = employeeService.getEmployee(employeeFromList.getEmployeeName());

        /**
         * Slutligen jämför vi den anställde vi plockade ut från listan (employee, förväntat resultat)
         * med den anställde som returneras från getEmployee (employee, faktiskt resultat)
         */
        Truth.assertWithMessage("employeeName differs")
                .that(employee.getEmployeeName())
                .isEqualTo(employeeFromList.getEmployeeName());

        Truth.assertWithMessage("employeeSalary differs")
                .that(employee.getEmployeeSalary())
                .isEqualTo(employeeFromList.getEmployeeSalary());

        Truth.assertWithMessage("employeeCategory differs")
                .that(employee.getEmployeeCategory())
                .isEqualTo(employeeFromList.getEmployeeCategory());
    }

    /**
     * @keywords Enhanced for-loop
     * Vi testar att det går att ta bort en anställd.
     *
     * Först skapar vi en anställd som vi sedan kan ta bort.
     * Sedan anropar vi getAllEmployees (som returnerar alla anställda)
     * Vi verifierar att listan inte innehåller den anställde vi tog bort.
     * Detta gör vi med hjälp av en Enhanced for-loop
     */
    @Test
    public void removeEmployee() throws Exception {
        Employee employeeToRemove = getTestEmployee();
        employeeService.addNewEmployee(employeeToRemove);

        employeeService.removeEmployee(employeeToRemove);

        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();

        /**
         * Du läser Enhanced for-loopen nedan enligt "för varje Employee i listan allEmployees".
         * Sedan väljer du vad du vill göra med varje anställd. I detta fall kollar vi att kunden
         * vi tog bort (employeeToRemove) inte finns med i listan bland anställda längre.
         */
        for (Employee employee : allEmployees) {
            Truth.assertWithMessage("Employee " + employeeToRemove.getEmployeeName() +
                    "has not been removed correctly")
            .that(employee.getEmployeeName())
            .isNotEqualTo(employeeToRemove.getEmployeeName());
        }
    }

    /**
     * @keywords Enhanced for-loop, ArrayList
     * Vi testar metoden getAllEmployees.
     *
     * För att verifiera all data i listan använder vi oss av en Enhanced for-loop.
     * För varje anställd i listan verifierar vi att:
     * - Namn inte är null
     * - Anställningskategori är giltig
     * - Lön är inom giltigt spann
     */
    @Test
    public void getAllEmployeesForLoop() {
        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();

        /**
         * Du läser Enhanced for-loopen nedan enligt "för varje Employee i listan allEmployees".
         * Sedan väljer du vad du vill göra med varje anställd. Enhanced for-loop kallas ibland
         * också för "for each loop"
         */
        for (Employee employee : allEmployees) {
            Truth.assertWithMessage("employeeName cannot be null")
                    .that(employee.getEmployeeName())
                    .isNotNull();

            Truth.assertWithMessage("Wrong employeeCategory")
                    .that(employee.getEmployeeCategory())
                    .isAnyOf(EmployeeCategory.CIO, EmployeeCategory.DEVELOPER, EmployeeCategory.DEVELOPER);

            Truth.assertWithMessage("employeeSalary must be greater than zero")
                    .that(employee.getEmployeeSalary())
                    .isAtLeast(MINIMUM_SALARY);

            Truth.assertWithMessage("employeeSalary must be greater than zero")
                    .that(employee.getEmployeeSalary())
                    .isAtMost(MAXIMUM_SALARY);
        }
    }

    /**
     * @keywords forEach, ArrayList
     * Detta test verifierar samma sak som getAllEmployeesForLoop.
     * Skillnaden är vi nu använder oss av funktionen forEach i Java 8.
     *
     * För att verifiera all data i listan använder vi oss av Java 8 forEach.
     * För varje anställd i listan verifierar vi att:
     * - Namn inte är null
     * - Anställningskategori är giltig
     * - Lön är inom giltigt spann
     */
    @Test
    public void getAllEmployeesForEach() {
        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();

        allEmployees.stream().forEach((employee) -> {
            Truth.assertWithMessage("employeeName cannot be null")
                    .that(employee.getEmployeeName())
                    .isNotNull();

            Truth.assertWithMessage("Wrong employeeCategory")
                    .that(employee.getEmployeeCategory())
                    .isAnyOf(EmployeeCategory.CIO, EmployeeCategory.DEVELOPER, EmployeeCategory.DEVELOPER);

            Truth.assertWithMessage("employeeSalary must be greater than zero")
                    .that(employee.getEmployeeSalary())
                    .isAtLeast(MINIMUM_SALARY);

            Truth.assertWithMessage("employeeSalary must be greater than zero")
                    .that(employee.getEmployeeSalary())
                    .isAtMost(MAXIMUM_SALARY);
        });
    }
}
