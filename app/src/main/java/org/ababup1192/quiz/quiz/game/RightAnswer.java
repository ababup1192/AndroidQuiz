package org.ababup1192.quiz.quiz.game;

/**
 * 正解の選択肢
 */
public class RightAnswer extends Answer {

    public RightAnswer(Integer id, Integer questionNum, String contents) {
        super(id, questionNum, contents, true);
    }

    public RightAnswer(Integer questionNum, String contents) {
        super(questionNum, contents, true);
    }

    public RightAnswer(String contents) {
        super(null, contents, true);
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "RightAnswer(" + id + "," + questionNum + "," + contents + ")";
    }
}
