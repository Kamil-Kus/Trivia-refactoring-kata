package kamil.kus.trivia;

public enum QuestionType {
    POP("Pop"),
    SPORT("Sports"),
    SCIENCE("Science"),
    ROCK("Rock");

    private final String name;

    QuestionType(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return name;
    }
}
