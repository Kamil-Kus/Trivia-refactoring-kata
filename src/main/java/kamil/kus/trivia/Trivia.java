package kamil.kus.trivia;

public interface Trivia {
    boolean add(String playerName);

    void roll(int roll);

    boolean wasCorrectlyAnswered();

    boolean wrongAnswer();
}
