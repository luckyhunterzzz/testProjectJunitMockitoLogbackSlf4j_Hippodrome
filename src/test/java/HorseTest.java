import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HorseTest {

    Horse HorseTest1;

    @Test
    void shouldThrowExceptionIfNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse(null, 0.0);
        });
    }
    @Test
    void shouldSendMessageThrowExceptionIfNameNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse(null, 0.0);
        });
        String expected = "Name cannot be null.";
        assertEquals(expected, exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n"})
    void shouldThrowExeptionIfNameBlank(String argument) {
        assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse(argument, 0.0);
        });
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n"})
    void shouldSendMessageIfNameBlank(String argument) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse(argument, 0.0);
        });
        String expected = "Name cannot be blank.";
        assertEquals(expected, exception.getMessage());
    }
    @Test
    void shouldThrowExeptionIfSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse("HorseTest1", -1.0);
        });
    }
    @Test
    void shouldThrowExeptionIfDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse("HorseTest1", 1.0, -10.0);
        });
    }
    @Test
    void shouldSendMessageIfSpeedNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse("HorseTest1", -1.0);
        });
        String expected = "Speed cannot be negative.";
        assertEquals(expected, exception.getMessage());
    }
    @Test
    void shouldSendMessageIfDistanceNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            HorseTest1 = new Horse("HorseTest1", 1.0, -10.0);
        });
        String expected = "Distance cannot be negative.";
        assertEquals(expected, exception.getMessage());
    }
    @Test
    void shouldGiveCorrectName() {
        HorseTest1 = new Horse("HorseTest1", 0.0);
        String expected = "HorseTest1";
        assertEquals(expected, HorseTest1.getName());
    }

    @Test
    void shouldGiveCorrectSpeed() {
        HorseTest1 = new Horse("HorseTest1", 5.0);
        double expected = 5.0;
        assertEquals(expected, HorseTest1.getSpeed());
    }

    @Test
    void shouldGiveCorrectDistance() {
        HorseTest1 = new Horse("HorseTest1", 0.0, 10.0);
        double expected = 10.0;
        assertEquals(expected, HorseTest1.getDistance());
    }
    @Test
    void shouldGiveCorrectDistanceWithoutDistanceInParam() {
        HorseTest1 = new Horse("HorseTest1", 0.0);
        double expected = 0;
        assertEquals(expected, HorseTest1.getDistance());
    }

    @Test
    void shouldCallgetRandomDouble() {
        HorseTest1 = new Horse("HorseTest1", 5.0);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            HorseTest1.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.8, 10.9})
    void shouldAssignCorrectValueToDistance(double paramValues) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            HorseTest1 = new Horse("HorseTest1", 5.0, 10.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(paramValues);
            HorseTest1.move();
            assertEquals(10.0 + 5.0 * paramValues, HorseTest1.getDistance());
        }
    }
}