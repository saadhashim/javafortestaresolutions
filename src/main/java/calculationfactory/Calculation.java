package calculationfactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculation {

    /**
     * Metoden tar två int som input-parametrar.
     * Metoden returnerar den input-paremeter som är störst.
     * Om input-paremetrarna är lika så kastas ett exception.
     * > betyder större än och < betyder mindre än. Dessa är logiska operatorer.
     */
    public int whichNumberIsLarger(int numberOne, int numberTwo) {
        if (numberOne > numberTwo) {
            return numberOne;
        } else if (numberOne < numberTwo) {
            return numberTwo;
        } else if (numberOne == numberTwo) {
            throw new IllegalArgumentException("Numbers are equal - Don't try to fool me");
        }
        return 0;
    }

    /**
     * Metoden tar två int som input-parametrar.
     * Metoden returnerar en boolean, sant om sifforna är samma
     * och falskt om siffrorna inte är samma.
     *
     * I java betyder "==" lika med
     * numberOne==numberTwo returnerar en boolean, därför räcker
     * med att vi skriver "return numberOne==numberTwo; Detta
     * betyder samma sak som:
     * if (numberOne==numberTwo) {
     *     return true;
     * } else {
     *     return false;
     * }
     */
    public boolean isNumbersEqual(int numberOne, int numberTwo) {
        return numberOne==numberTwo;
    }

    /**
     * Metoden tar två int som input-parametrar.
     * Metoden returnerar summan av de två input-parametrarna
     * + används för att addera två tal
     */
    public int addition(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    /**
     * Metoden tar ett LocalDate (Java 8) som inmparemeter
     * Metoden returnerar antalet dagar till din födelsedag
     */
    public int daysUntilYourBirthday(LocalDate yourBirthDay) {
        LocalDate thisYearBirthDay =
                LocalDate.of(LocalDate.now().getYear(), yourBirthDay.getMonth(), yourBirthDay.getDayOfMonth());

        if (thisYearBirthDay.isBefore(LocalDate.now())) {
            return (int) ChronoUnit.DAYS.between(LocalDate.now(), thisYearBirthDay.plusYears(1));
        }
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), thisYearBirthDay);
    }
}
