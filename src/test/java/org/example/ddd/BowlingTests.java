package org.example.ddd;

import View.TableauAffichage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BowlingTests {

    private Game game;
    private TableauAffichage displayBoard;

    @BeforeEach
    public void setUp() {
        displayBoard = Mockito.mock(TableauAffichage.class);
        game = new Game(displayBoard);
    }

    @Test
    public void check_20LaunchesAt0_ShouldReturn0() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score(), "20 rolls of 0 should result in a score of 0.");
    }

    @Test
    public void check_20LaunchesAt1_ShouldReturn20() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score(), "20 rolls of 1 should result in a score of 20.");
    }

    @Test
    public void check_1Spare_ShouldReturn16() {
        game.roll(5);
        game.roll(5); // Spare
        game.roll(3);
        for (int i = 0; i < 17; i++) {
            game.roll(0);
        }
        assertEquals(16, game.score(), "A spare followed by a 3 and then all zeros should result in a score of 16.");
    }

    @Test
    public void check_1Strike_ShouldReturn24() {
        game.roll(10); // Strike
        game.roll(3);
        game.roll(4);
        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }
        assertEquals(24, game.score(), "A strike followed by a 3, a 4, and then all zeros should result in a score of 24.");
    }

    @Test
    public void check_PerfectGame_ShouldReturn300() {
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }
        assertEquals(300, game.score(), "A perfect game of 12 strikes should result in a score of 300.");
    }

    @Test
    void check_Game_ShouldConnectToDisplayBoard() {
        verify(displayBoard, times(1)).seConnecter();
    }

    @Test
    void check_Spare_ShouldNotifyDisplayBoardOnce() {
        game.roll(5);
        game.roll(5);
        verify(displayBoard, times(1)).showSpare();
    }

    @Test
    void check_FirstStrike_ShouldNotifyDisplayBoardWithFirst() {
        game.roll(10);
        verify(displayBoard, times(1)).showStrike(TableauAffichage.StrikeSerie.PREMIER);
    }

    @Test
    void check_SecondStrike_ShouldNotifyDisplayBoardWithSecond() {
        game.roll(10);
        game.roll(0);
        game.roll(0);
        game.roll(10);
        verify(displayBoard, times(1)).showStrike(TableauAffichage.StrikeSerie.SECOND);
    }

    @Test
    void check_ThirdAndSubsequentStrikes_ShouldNotifyDisplayBoardAccordingly() {
        game.roll(10);
        game.roll(10);
        game.roll(10);
        verify(displayBoard, times(1)).showStrike(TableauAffichage.StrikeSerie.TROISIEME_ET_PLUS);
    }

    @Test
    void check_EndGame_ShouldCheckAndUpdateBestScores() {
        game.endGame();
        verify(displayBoard, times(1)).bestScores();
        verify(displayBoard, times(1)).updateBestScores(anyInt());
    }
}
