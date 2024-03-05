package org.example.ddd;

import View.TableauAffichage;

public class Game {
    private int score = 0;
    private int currentFrame = 1;
    private int currentRoll = 1;
    private int[] rolls = new int[21];

    private TableauAffichage displayBoard;

    public Game(TableauAffichage displayBoard) {
        this.displayBoard = displayBoard;
    }

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }


    public int score() {
        int score = 0;
        int rollIndex = 1;
        for (int frame = 1; frame <= 10; frame++) {
            if (isStrike(rollIndex)) {
                score += 10 + rolls[rollIndex + 1] + rolls[rollIndex + 2];
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                score += 10 + rolls[rollIndex + 2];
                rollIndex += 2;
            } else {
                score += rolls[rollIndex] + rolls[rollIndex + 1];
                rollIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }
}
