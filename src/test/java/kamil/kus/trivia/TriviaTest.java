package kamil.kus.trivia;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriviaTest {

    @Test
    public void characterisationTest() {
        for (int seed = 1; seed < 50_000; seed++) {
            String expectedOutput = extractOutput(new Random(seed), new Game());
            String actualOutput = extractOutput(new Random(seed), new GameRefactor());
            assertEquals(expectedOutput, actualOutput);
        }
    }


    private String extractOutput(Random rand, Trivia aGame) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(byteArrayOutputStream)) {
            System.setOut(output);
            aGame.add("Jamal");
            aGame.add("Donald");
            aGame.add("Joe");
            aGame.add("Victor");
            aGame.add("David");

            boolean notAWinner;
            do {
                aGame.roll(rand.nextInt(5) + 1);
                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.wasCorrectlyAnswered();
                }
            } while (notAWinner);
        }
        return byteArrayOutputStream.toString();
    }

}
