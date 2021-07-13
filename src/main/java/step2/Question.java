package step2;

public abstract class Question {

    private String text;
    private String[] answerChoices;
    private int id;

    public Question(String text, String[] answerChoices, int id) {
        this.text = text;
        this.answerChoices = answerChoices;
        this.id = id;
    }

    public boolean match(Answer answer) {
        return false;
    }

    abstract public boolean match(int expected, int actual);

    public int indexOf(String matchingAnswerChoice) {
        for (int i = 0; i < answerChoices.length; i++) {
            if (answerChoices[i].equals(matchingAnswerChoice)) {
                return i;
            }
        }
        return -1;
    }


    public String getText() {
        return text;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public String getAnswerChoice(int i) {
        return answerChoices[i];
    }

    public int getId() {
        return id;
    }
}
