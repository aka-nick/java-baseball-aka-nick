package baseball.object.number;

import baseball.object.generic.BallCount;
import baseball.object.number.generator.NumbersGenerator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class WinningNumbers {

    private NumbersGenerator numbersGenerator;
    private List<Integer> winningNumbers;

    public WinningNumbers(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
        this.winningNumbers = numbersGenerator.generate();
    }

    public BallCount ballCounting(BaseballNumbers baseballNumbers) {
        return new BallCount(
                countingStrikes(baseballNumbers),
                countingBalls(baseballNumbers));
    }

    private long countingStrikes(BaseballNumbers baseballNumbers) {
        return IntStream.range(0, 3)
                .filter(i -> Objects.equals(
                        winningNumbers.get(i), baseballNumbers.get().get(i)))
                .count();
    }

    private long countingBalls(BaseballNumbers baseballNumbers) {
        long countOfOnlyContains = baseballNumbers.get().stream()
                .filter(winningNumbers::contains)
                .count();
        long countOfExactMatches = countingStrikes(baseballNumbers);
        return countOfOnlyContains - countOfExactMatches;
    }

}
