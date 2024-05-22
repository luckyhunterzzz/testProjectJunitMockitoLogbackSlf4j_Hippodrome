import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    Hippodrome hippodromeTest1;
    @Test
    void shouldThrowExceptionIfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            hippodromeTest1 = new Hippodrome(null);
        });
    }
    @Test
    void shouldSendMessageIfNull() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> {
            hippodromeTest1 = new Hippodrome(null);
        });
        String expected = "Horses cannot be null.";
        assertEquals(expected, actual.getMessage());
    }
    @Test
    void shouldThrowExceptionIfListIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            hippodromeTest1 = new Hippodrome(horses);
        });
    }
    @Test
    void shouldSendMessageIfListIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> {
            hippodromeTest1 = new Hippodrome(horses);
        });
        String expected = "Horses cannot be empty.";
        assertEquals(expected, actual.getMessage());
    }
    @Test
    void shouldGiveCorrectLisOfHorses() {
        List<Horse> expectedHorses = new ArrayList<Horse>();
        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse("HorseTest"  + (i + 1), 1.0));
        }
        hippodromeTest1 = new Hippodrome(expectedHorses);
        assertEquals(expectedHorses, hippodromeTest1.getHorses());
    }

    @Test
    void shouldCallMoveToEveryHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse testHorse:horses) {
            Mockito.verify(testHorse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        List <Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            testHorses.add(new Horse("testHorseName" + i, i, i));
        }
        Hippodrome testHippodrome = new Hippodrome(testHorses);
        assertEquals(testHorses.get(49), testHippodrome.getWinner());
    }
}