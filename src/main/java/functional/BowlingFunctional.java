package functional;

import java.util.ArrayList;
import java.util.List;

public class BowlingFunctional {
    static final List<Integer> rolls = new ArrayList<>();

    public static void addRoll(int pins) {
        rolls.add(pins);
    }

    private static boolean isStrike(int index) {
        return rolls.get(index) == 10;
    }

    private static boolean isSpare(int index) {
        return rolls.get(index) + rolls.get(index + 1) == 10;
    }

    public static int calculateScore() {
        int score = 0;
        for (int frame = 0, rollIndex = 0; frame < 10 && rollIndex < rolls.size(); frame++) {
            score += scoreForFrame(rollIndex);
            rollIndex += frameRolls(rollIndex);
        }
        return score;
    }

    private static int scoreForFrame(int rollIndex) {
        return isStrike(rollIndex) ? 10 + strikeBonus(rollIndex) :
                isSpare(rollIndex) ? 10 + spareBonus(rollIndex) :
                        openFrameScore(rollIndex);
    }

    private static int strikeBonus(int index) {
        return bonusRoll(index, 1) + bonusRoll(index, 2);
    }

    private static int spareBonus(int index) {
        return bonusRoll(index, 2);
    }

    private static int openFrameScore(int index) {
        return rolls.get(index) + bonusRoll(index, 1);
    }

    private static int bonusRoll(int index, int offset) {
        int newIndex = index + offset;
        return newIndex < rolls.size() ? rolls.get(newIndex) : 0;
    }

    private static int frameRolls(int index) {
        return isStrike(index) ? 1 : 2;
    }
}
