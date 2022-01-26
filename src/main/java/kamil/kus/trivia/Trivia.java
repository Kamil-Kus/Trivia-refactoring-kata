package kamil.kus.trivia;

public interface Trivia {
    void add(String playerName);

    void roll(int roll);

    boolean wasCorrectlyAnswered();

    boolean wrongAnswer();
}
