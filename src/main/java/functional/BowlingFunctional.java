package functional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingFunctional {

    public static List<Integer> addRoll(List<Integer> rolls, int pins) {
        return Stream.concat(rolls.stream(), Stream.of(pins)).collect(Collectors.toList());
    }

    public static boolean isStrike(List<Integer> frame) {
        return frame.get(0) == 10;
    }

    public static boolean isSpare(List<Integer> frame) {
        return frame.size() > 1 && frame.get(0) + frame.get(1) == 10;
    }

    public static int calculateFrameScore(List<Integer> frame, List<Integer> nextRolls) {
        if (isStrike(frame)) {
            int strikeBonus = nextRolls.stream().limit(2).mapToInt(Integer::intValue).sum();
            return 10 + strikeBonus;
        } else if (isSpare(frame)) {
            int spareBonus = nextRolls.isEmpty() ? 0 : nextRolls.get(0);
            return 10 + spareBonus;
        } else {
            return frame.stream().mapToInt(Integer::intValue).sum();
        }
    }


    public static int calculateScore(List<List<Integer>> frames) {
        List<Integer> allRolls = frames.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        int score = 0;
        int rollIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(List.of(allRolls.get(rollIndex)))) { // Strike
                score += 10 + allRolls.get(rollIndex + 1) + allRolls.get(rollIndex + 2);
                rollIndex++;
            } else if (isSpare(List.of(allRolls.get(rollIndex), allRolls.get(rollIndex + 1)))) {
                score += 10 + allRolls.get(rollIndex + 2);
                rollIndex += 2;
            } else {
                score += allRolls.get(rollIndex) + allRolls.get(rollIndex + 1);
                rollIndex += 2;
            }
        }
        return score;
    }
}
