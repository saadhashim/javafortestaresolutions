package controlflowfactory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ricjar on 21/06/2016.
 */
public class ControlFlow {

    private final ArrayList<String> places = new ArrayList<String>(
            Arrays.asList("Madrid", "Stockholm", "Reykavik", "Rio de Janeiro"));

    /**
     * @keywords if-else
     * Syntax:
     * if(Boolean_expression){
     *    sats (exekveras om Boolean_expression �r sann)
     * }else{
     *    sats (exekveras om Boolean_expression �r falsk)
     * }
     *
     * Om str�ngen motsvarar "if" (med stora eller sm� bokst�ver) returneras true.
     * Annars returneras false.
     */
    public boolean ifElse(String stringEqualsIf) {
        if (stringEqualsIf.equalsIgnoreCase("if")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @keywords switch
     *
     * Ett annat s�tt att kontrollera fl�det �r med en switch.
     * En switch ger dig m�jlighet att testa ett intervall av v�rden f�r dina variabler.
     * En switch kan anv�ndas i st�llet f�r l�nga komplicerad if-else satser.
     *
     * Syntax:
     * switch (variabel) {
     *     case XXX: //XXX m�ste vara av samma datatyp som variabeln i switchen. Om variabeln matchar detta case s� g�r vi till raden nedan (statement).
     *     sats
     *     default: //Om inget case matchar variabeln i switchen s� exekveras default
     *     sats
     * }
     */
    public double getAnimalSpeed(Animal animal) {
        switch (animal) {
        case ELK:
            return 72.4;
        case SQUIRREL:
            return 20;
        case GIRAFFE:
            return 52;
        case HORSE:
            return 88;
        case MOUSE:
            return 13;
        case LION:
            return 80;
        case PIG:
            return 17.7;
        case ZEBRA:
            return 64.4;
        default:
            throw new IllegalArgumentException("Animal not found");
        }
    }

    /**
     * @keywords for loop
     *
     * Ett s�tt att iterera igenom en lista �r med hj�lp av en for loop.
     *
     * Syntax:
     * for (initieringen; avslutningen; �kningen) {
     *      sats
     * }
     *
     * Initieringen initierar loopen - den exekveras en g�ng n�r loopen b�rjar.
     * N�r avslutningen utv�rderas till false avslutas loopen.
     * �kningen anropas efter varje iteration genom loopen, h�r kan du �ka eller minska ett v�rde.
     */
    public void forLoop() {
        for (int loopValue = 0; loopValue < places.size(); loopValue++) {
            System.out.println(loopValue);
            if (places.get(loopValue).equals("Rio de Janeiro")) {
                System.out.println("Ooh, Rio de Janeiro - That's hot");
            }
        }
    }

    /**
     * @keywords enhanced for loop
     *
     * Ett annat s�tt att iterera igenom en lista �r med hj�lp av en enhanced for loop.
     *
     * Syntax:
     * for (Datatyp variabel : lista) {
     *      sats (g�r n�got med ett eller alla v�rden i listan)
     * }
     */
    public void enhancedForLoop() {
        for (String place : places) {
            System.out.println(place);
            if (place.equalsIgnoreCase("Rio de Janeiro")) {
                System.out.println("Ooh, Rio de Janeiro - That's hot!");
            }
        }

    }

    /**
     * @keywords while
     *
     * Ett tredje s�tt att iterera igenom en lista �r med hj�lp while.
     *
     * Syntax:
     * while (boolean) {
     *      sats (g�r n�got med ett eller alla v�rden i listan)
     * }
     */
    public void whileLoop() {
        int i = 0;
        while (i < places.size()) {
            System.out.println(places.get(i));
            if (places.get(i).equals("Rio de Janeiro")) {
                System.out.println("Ooh, Rio de Janeiro - That's hot!");
            }
        }
    }

    /**
     * @keywords forEach
     *
     * Ett tredje s�tt att iterera igenom en lista �r med hj�lp while.
     *
     * Syntax:
     * lista.stream().forEach((listv�rde) -> {
     *      sats (g�r n�got med ett eller alla v�rden i listan)
     * });
     *
     * stream() skapar en str�m som representerar en sekvens av element
     * forEach() f�r varje v�rde i listan
     * (listv�rde) -> g�r n�got med alla v�rden i listan
     */
    public void forEach() {
        places.stream().forEach((place) -> {
            System.out.println(place);
            if (place.equals("Rio de Janeiro")) {
                System.out.println("Ooh, Rio de Janeiro - That's hot!");
            }
        });
    }
}
