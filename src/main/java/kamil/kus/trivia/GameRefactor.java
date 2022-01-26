package kamil.kus.trivia;

import java.util.ArrayList;
import java.util.List;


public class GameRefactor implements Trivia {
    private final List<Player> playersObj = new ArrayList<>();

    private final List<String> popQuestions = new ArrayList<>();
    private final List<String> scienceQuestions = new ArrayList<>();
    private final List<String> sportsQuestions = new ArrayList<>();
    private final List<String> rockQuestions = new ArrayList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameRefactor() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        playersObj.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playersObj.size());
        return true;
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayer().name() + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (getCurrentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(getCurrentPlayer().name() + " is getting out of the penalty box");
                getCurrentPlayer().move(roll);
                System.out.println(getCurrentPlayer().name() + "'s new location is " + getCurrentPlayer().place());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(getCurrentPlayer().name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            getCurrentPlayer().move(roll);
            System.out.println(getCurrentPlayer().name() + "'s new location is " + getCurrentPlayer().place());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private Player getCurrentPlayer() {
        return playersObj.get(currentPlayer);
    }

    private void askQuestion() {
        switch (currentCategory()) {
            case "Pop" -> System.out.println(popQuestions.remove(0));
            case "Science" -> System.out.println(scienceQuestions.remove(0));
            case "Sports" -> System.out.println(sportsQuestions.remove(0));
            case "Rock" -> System.out.println(rockQuestions.remove(0));
        }
    }


    private String currentCategory() {
        if (getCurrentPlayer().place() % 4 == 0) return QuestionType.POP.getCategoryName();
        if (getCurrentPlayer().place() % 4 == 1) return QuestionType.SCIENCE.getCategoryName();
        if (getCurrentPlayer().place() % 4 == 2) return QuestionType.SPORT.getCategoryName();
        return QuestionType.ROCK.getCategoryName();
    }

    public boolean wasCorrectlyAnswered() {
        if (getCurrentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                getCurrentPlayer().addCoin();
                System.out.println(getCurrentPlayer().name()
                        + " now has "
                        + getCurrentPlayer().purses()
                        + " Gold Coins.");

                boolean winner = getCurrentPlayer().didPlayerWin();
                moveToNextPlayer();
                return winner;
            } else {
                moveToNextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            getCurrentPlayer().addCoin();
            System.out.println(getCurrentPlayer().name() + " now has " + getCurrentPlayer().purses() + " Gold Coins.");
            boolean winner = getCurrentPlayer().didPlayerWin();
            moveToNextPlayer();
            return winner;
        }
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == playersObj.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer().name() + " was sent to the penalty box");
        getCurrentPlayer().moveInToPenaltyBox();
        moveToNextPlayer();
        return true;
    }
}
