package controlflowfactory;

import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class ControlFlowTest {
    ControlFlow controlFlow = new ControlFlow();

    @Test
    public void ifElseTrue() {
        assertWithMessage("String should equal if")
                .that(controlFlow.ifElse("if"))
                .isTrue();
    }

    @Test
    public void ifElseFalse() {
        assertWithMessage("String should not equal if")
                .that(controlFlow.ifElse("else"))
                .isFalse();
    }

    @Test
    public void getAnimalSpeed() {
        assertWithMessage("Wrong animal speed")
        .that(controlFlow.getAnimalSpeed(Animal.PIG))
                .isEqualTo(17.7);
    }
}

