package org.example;

import org.junit.Test;
import org.junit.gen5.api.BeforeEach;

public class BowlingTests {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void check_20LaunchesAt0_ShouldReturn () {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assert game.score() == 0;
    }

    @Test
    public void check_20LaunchesAt1_ShouldReturn20() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assert game.score() == 20;
    }

    @Test
    public void check_1Spare_ShouldReturn16() {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        for (int i = 0; i < 17; i++) {
            game.roll(0);
        }
        assert game.score() == 16;
    }

    @Test
    public void check_1Strike_ShouldReturn24() {
        game.roll(10);
        game.roll(3);
        game.roll(4);
        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }
        assert game.score() == 24;
    }

    @Test
    public void check_PerfectGame_ShouldReturn300() {
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }
        assert game.score() == 300;
    }
}
