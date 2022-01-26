package kamil.kus.trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Questions {
    private final Map<QuestionType, List<String>> questionStorage = new HashMap<>();

    public Map<QuestionType, List<String>> getQuestionStorage() {
        return questionStorage;
    }

    public Questions() {
        questionStorage.put(QuestionType.POP, new ArrayList<>());
        questionStorage.put(QuestionType.SCIENCE, new ArrayList<>());
        questionStorage.put(QuestionType.SPORT, new ArrayList<>());
        questionStorage.put(QuestionType.ROCK, new ArrayList<>());
        for (int i = 0; i < 50; i++) {
            questionStorage.get(QuestionType.POP).add("Pop Question " + i);
            questionStorage.get(QuestionType.SCIENCE).add(("Science Question " + i));
            questionStorage.get(QuestionType.SPORT).add(("Sports Question " + i));
            questionStorage.get(QuestionType.ROCK).add("Rock Question " + i);
        }
    }

    public void askQuestion(int questionType) {
        System.out.println(getQuestionStorage()
                .get(currentCategory(questionType))
                .remove(0));
    }

    public QuestionType currentCategory(int playerLocationIndex) {
        return switch (playerLocationIndex % 4) {
            case 0 -> QuestionType.POP;
            case 1 -> QuestionType.SCIENCE;
            case 2 -> QuestionType.SPORT;
            case 3 -> QuestionType.ROCK;
            default -> throw new IllegalStateException("Unexpected value: " + playerLocationIndex % 4);
        };
    }
}
