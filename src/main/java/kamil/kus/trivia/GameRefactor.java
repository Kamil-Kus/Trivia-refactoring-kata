package kamil.kus.trivia;

import java.util.ArrayList;
import java.util.List;

public class GameRefactor implements Trivia {
    private final List<Player> playersObj = new ArrayList<>();
    private final Questions questions = new Questions();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameRefactor() {
    }

    public void add(String playerName) {
        playersObj.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playersObj.size());
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
                System.out.println("The category is " + questions.currentCategory(getCurrentPlayer().place()).getCategoryName());
                questions.askQuestion(getCurrentPlayer().place());
            } else {
                System.out.println(getCurrentPlayer().name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            getCurrentPlayer().move(roll);
            System.out.println(getCurrentPlayer().name() + "'s new location is " + getCurrentPlayer().place());
            System.out.println("The category is " + questions.currentCategory(getCurrentPlayer().place()).getCategoryName());
            questions.askQuestion(getCurrentPlayer().place());
        }

    }

    private Player getCurrentPlayer() {
        return playersObj.get(currentPlayer);
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
