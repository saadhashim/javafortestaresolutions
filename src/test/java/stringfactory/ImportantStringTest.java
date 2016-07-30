package stringfactory;

import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class ImportantStringTest {

    ImportantString importantString = new ImportantString();
    private final String expectedBalance = "1000";

    /**
     * @keywords substring
     * @reference https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
     * Vi plockar ut balansen fr�n str�ngen accountBalance.
     *
     * Detta g�r vi med hj�lp av metoden substring()
     * Delstr�ngen (substring) b�rjar med tecknet vid det angivna indexet
     * och str�cker sig till slutet av str�ngen eller upp till endIndex -
     * om ett andra argumentet ges.
     */
    @Test
    public void getAccountBalanceUsingSubstring() {
        assertWithMessage("Wrong balance")
                .that(importantString.getAccountBalance().substring(9))
                .isEqualTo(expectedBalance);
    }

    /**
     * @keywords contains
     * @reference https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#contains(java.lang.CharSequence)
     * Vi kollar om Str�ngen accountBalance inneh�ller balansen.
     *
     * Detta g�r vi med hj�lp av metoden contains
     * Contains returnerar true om str�ngen (accountBalance) inneh�ller den specificerade str�ngen ("1000").
     */
    @Test
    public void getAccountBalanceUsingContains() {
        assertWithMessage("Wrong balance")
                .that(importantString.getAccountBalance())
                .contains(expectedBalance);
    }

    /**
     * @keywords split
     * @reference https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)
     * Vi plockar ut balansen fr�n str�ngen accountBalance.
     *
     * Detta g�r vi med hj�lp av metoden split
     * Split splittar str�ngen vid det tecken du skickar som inparameter.
     */
    @Test
    public void getAccountBalanceUsingSplit() {
        //Vi splittar str�ngen p� mellanslaget. Str�ngen: "Balance: 1000" kommer allts� delas upp i tv� str�ngar
        String[] textAndBalance = importantString.getAccountBalance().split(" ");

        //Vi h�mtar den andra str�ngen, "1000", index b�rjar p� 0
        String balance = textAndBalance[1];
        assertWithMessage("Wrong balance")
                .that(balance)
                .isEqualTo(expectedBalance);
    }

    /**
     * @keywords replace
     * @reference https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#replace(char,%20char)
     * Vi plockar ut balansen fr�n str�ngen accountBalance.
     *
     * Detta g�r vi med hj�lp av metoden replace
     * Replace tar bort de tecken ur str�ngen du skickar in i f�rsta inparemetern
     * och ers�tter dem med det du skickar in i andra inparametern
     */
    @Test
    public void getAccountBalanceUsingReplace() {
        //Vi ers�tter "Balance: " med tomma str�ngen, vi tar allts� bort dessa tecken fr�n str�ngen
        assertWithMessage("Wrong balance")
                .that(importantString.getAccountBalance().replace("Balance: ", ""))
                .isEqualTo(expectedBalance);
    }

    /**
     * @keywords regular expression
     * @reference https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     * Vi plockar ut balansen fr�n str�ngen accountBalance.
     *
     * Detta g�r vi med hj�lp av ett regulj�rt utrryck.
     * Ett regulj�rt utrryck �r ett textm�nster som anv�nds f�r att passa in i text-str�ngar.
     * Regulj�ra uttryck anv�nds p� tre olika s�tt: vanlig texts�kning, s�k-och-ers�tt, samt vid delning.
     */
    @Test
    public void getAccountBalanceUsingRegexp() {
        /**
         * Vi tar bort allt f�rutom siffror punkt och komma fr�n str�ngen accountBalance
         * ^ betyder ta bort all f�rutom det som f�ljer
         * 0-9 betyder siffrorna 0-9
         * ., eftersom det kan finnas decimalavskiljare i siffran
         */
        assertWithMessage("Wrong balance")
                .that(importantString.getAccountBalance().replaceAll("[^0-9.,]", ""))
                .isEqualTo(expectedBalance);
    }
}
