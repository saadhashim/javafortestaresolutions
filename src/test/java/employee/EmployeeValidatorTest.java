package employee;

import com.google.common.truth.Truth;
import employee.impl.EmployeeValidator;
import org.junit.Test;

public class EmployeeValidatorTest {

    EmployeeValidator employeeValidator = new EmployeeValidator();

    /*
   * Detta är ett negativt test.
   * I try-satsen sker det först ett anrop mot nameLengthValidator.
   * Vi förväntar oss därefter att hamna i catch-satsen I och med att
   * testet ska kasta IllegalArgumentException.
   * Hamnar vi inte i catch-satsen kommer testet fallera.
   */
    @Test
    public void verifyNameLengthOption1() {
        try {
            employeeValidator.validateNameLength(null);
            Truth.THROW_ASSERTION_ERROR.fail("Name cannot be null");
        } catch (IllegalArgumentException e) {
            System.out.println("Testet passerade");
        }
    }

    /*
   * Detta är ett negativt test.
   * Skillnaden mot förra testet är att vi nu använder oss av expected-parametern.
   * Här talar vi om att testet förvänas kasta ett visst Exception.
   */
    @Test(expected = IllegalArgumentException.class)
    public void verifyNameLengthOption2() {
        employeeValidator.validateNameLength(null);
    }

    /*
   * Detta är ett positivt test.
   * Strängen "Karl" ska vara tillåten som namn
   */
    @Test
    public void verifyNameLength() {
        employeeValidator.validateNameLength("Karl");
    }
}
