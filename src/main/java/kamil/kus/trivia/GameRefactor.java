package kamil.kus.trivia;

import java.util.ArrayList;
import java.util.List;


public class GameRefactor implements Trivia {
    private final List<Player> playersObj = new ArrayList<>();
    private final List<String> players = new ArrayList<>();
    boolean[] inPenaltyBox = new boolean[6];

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
        players.add(playerName);
        inPenaltyBox[playersObj.size()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                playersObj.get(currentPlayer).setPlace(playersObj.get(currentPlayer).place() + roll);
                if (playersObj.get(currentPlayer).place() > 11)
                    playersObj.get(currentPlayer).setPlace(playersObj.get(currentPlayer).place() - 12);

                System.out.println(playersObj.get(currentPlayer).name() + "'s new location is " + playersObj.get(currentPlayer).place());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(playersObj.get(currentPlayer).name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            playersObj.get(currentPlayer).setPlace(playersObj.get(currentPlayer).place() + roll);
            if (playersObj.get(currentPlayer).place() > 11)
                playersObj.get(currentPlayer).setPlace(playersObj.get(currentPlayer).place() - 12);

            System.out.println(playersObj.get(currentPlayer).name() + "'s new location is " + playersObj.get(currentPlayer).place());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

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
        if (playersObj.get(currentPlayer).place() % 4 == 0) return QuestionType.POP.getCategoryName();
        if (playersObj.get(currentPlayer).place() % 4 == 1) return QuestionType.SCIENCE.getCategoryName();
        if (playersObj.get(currentPlayer).place() % 4 == 2) return QuestionType.SPORT.getCategoryName();
        return QuestionType.ROCK.getCategoryName();
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                playersObj.get(currentPlayer).addCoin();
                System.out.println(playersObj.get(currentPlayer).name()
                        + " now has "
                        + playersObj.get(currentPlayer).purses()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            playersObj.get(currentPlayer).addCoin();
            System.out.println(playersObj.get(currentPlayer).name() + " now has " + playersObj.get(currentPlayer).purses() + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(playersObj.get(currentPlayer).purses() == 6);
    }
}
