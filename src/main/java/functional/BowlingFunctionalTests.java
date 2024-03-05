package functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.IntStream;

public class BowlingFunctionalTests {

    @Test
    public void check_20ZeroThrows_ShouldReturn0() {
        var rolls = IntStream.generate(() -> 0).limit(20).boxed().toList();
        int score = BowlingFunctional.calculateScore(Collections.singletonList(rolls));
        assertEquals(0, score, "Score should be 0 for a game of all zero-point throws.");
    }

    @Test
    public void check_20OnePointThrows_ShouldReturn20() {
        var rolls = IntStream.generate(() -> 1).limit(20).boxed().toList();
        int score = BowlingFunctional.calculateScore(Collections.singletonList(rolls));
        assertEquals(20, score, "Score should be 20 for a game of all one-point throws.");
    }

    @Test
    public void check_1Spare_ShouldReturn16() {
        var rolls = IntStream.concat(IntStream.of(5, 5, 3), IntStream.generate(() -> 0).limit(17)).boxed().toList();
        int score = BowlingFunctional.calculateScore(Collections.singletonList(rolls));
        assertEquals(16, score, "Score should be 16 for a spare followed by a three-point throw.");
    }

    @Test
    public void check_1Strike_ShouldReturn24() {
        var rolls = IntStream.concat(IntStream.of(10, 3, 4), IntStream.generate(() -> 0).limit(16)).boxed().toList();
        int score = BowlingFunctional.calculateScore(Collections.singletonList(rolls));
        assertEquals(24, score, "Score should be 24 for a strike followed by throws of three and four points.");
    }

    @Test
    public void check_PerfectGame_ShouldReturn300() {
        var rolls = IntStream.generate(() -> 10).limit(12).boxed().toList();
        int score = BowlingFunctional.calculateScore(Collections.singletonList(rolls));
        assertEquals(300, score, "Score should be 300 for a perfect game.");
    }
}
