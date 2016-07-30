package calculationfactory;

import org.junit.Test;

import java.time.LocalDate;

import static com.google.common.truth.Truth.assertWithMessage;

public class CalculationTest {
    Calculation calculation = new Calculation();

    /**
     * @keywords Logiska operatorer > <
     * Vi testar metoden whichNumberIsLarger.
     *
     * Vi testar att första siffran av typen int är större än andra siffran
     */
    @Test
    public void whichNumberIsLargerFirstNumber() {
        assertWithMessage("First number should be largest")
                .that(calculation.whichNumberIsLarger(2,1))
                .isEqualTo(2);
    }

    /**
     * @keywords Logisk operator > <
     * Vi testar metoden whichNumberIsLarger.
     *
     * Vi testar att första andra siffran av typen int är större än första siffran
     */
    @Test
    public void whichNumberIsLargerSecondNumber() {
        assertWithMessage("Second number should be largest")
                .that(calculation.whichNumberIsLarger(1,2))
                .isEqualTo(2);
    }

    /*
   * @keywords expected
   * Detta är ett negativt test.
   *
   * I expected-parametern talar vi om att testet förvänas kasta ett visst Exception.
   */
    @Test (expected = IllegalArgumentException.class)
    public void whichNumberIsLargerSameNumber() {
        calculation.whichNumberIsLarger(1, 1);
    }

    /**
     * @keywords Logisk operator ==
     * Vi testar metoden isNumbersEqual.
     *
     * Vi testar metoden med två siffror som är likvärdiga.
     * Vi förväntar oss alltså att metoden ska returnera true.
     */
    @Test
    public void isNumbersEqualTrue() {
        assertWithMessage("Numbers should be equal")
                .that(calculation.isNumbersEqual(1, 1))
                .isEqualTo(true);
    }

    /**
     * @keywords Logisk operator ==
     * Vi testar metoden isNumbersEqual.
     *
     * Vi testar metoden med två siffror som inte är likvärdiga.
     * Vi förväntar oss alltså att metoden ska returnera false.
     */
    @Test
    public void isNumbersEqualFalse() {
        assertWithMessage("numbers should not be equal")
                .that(calculation.isNumbersEqual(1,4))
                .isEqualTo(false);
    }

    /**
     * @keywords Arithmetic Operator +
     * Vi testar metoden isNumbersEqual.
     *
     * Vi testar metoden med två siffror som inte är likvärdiga.
     * Vi förväntar oss alltså att metoden ska returnera false.
     */
    @Test
    public void addition() {
        assertWithMessage("Wrong result of the addition")
                .that(calculation.addition(1,1))
                .isEqualTo(2);
    }

    @Test
    public void daysUntilYourBirthdayTest() {
        int daysUntilBDay = calculation.daysUntilYourBirthday(LocalDate.of(1982, 11, 24));
    }
}
