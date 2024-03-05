package functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingFunctionalTests {

    @BeforeEach
    public void setup() {
        BowlingFunctional.rolls.clear();
    }

    @Test
    public void check_20ZeroThrows_ShouldReturn0() {
        addRolls(20, 0);
        assertEquals(0, BowlingFunctional.calculateScore(), "Score should be 0 for a game of all zero-point throws.");
    }

    @Test
    public void check_20OnePointThrows_ShouldReturn20() {
        addRolls(20, 1);
        assertEquals(20, BowlingFunctional.calculateScore(), "Score should be 20 for a game of all one-point throws.");
    }

    @Test
    public void check_1Spare_ShouldReturn16() {
        addRolls(2, 5); // Spare
        BowlingFunctional.addRoll(3);
        addRolls(17, 0);
        assertEquals(16, BowlingFunctional.calculateScore(), "Score should be 16 for a spare followed by a three-point throw.");
    }

    @Test
    public void check_1Strike_ShouldReturn24() {
        BowlingFunctional.addRoll(10);
        BowlingFunctional.addRoll(3);
        BowlingFunctional.addRoll(4);
        addRolls(16, 0);
        assertEquals(24, BowlingFunctional.calculateScore(), "Score should be 24 for a strike followed by throws of three and four points.");
    }

    @Test
    public void check_PerfectGame_ShouldReturn300() {
        addRolls(12, 10);
        assertEquals(300, BowlingFunctional.calculateScore(), "Score should be 300 for a perfect game.");
    }

    // Helper method to add multiple rolls with the same number of pins
    private void addRolls(int count, int pins) {
        for (int i = 0; i < count; i++) {
            BowlingFunctional.addRoll(pins);
        }
    }
}
