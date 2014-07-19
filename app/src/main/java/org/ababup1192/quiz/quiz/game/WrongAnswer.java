package org.ababup1192.quiz.quiz.game;

/**
 * 不正解の選択肢
 */
public class WrongAnswer extends Answer {

    public WrongAnswer(Integer id, Integer questionNum, String contents) {
        super(id, questionNum, contents, false);
    }

    public WrongAnswer(Integer questionNum, String contents) {
        super(questionNum, contents, false);
    }


    public WrongAnswer(String contents) {
        super(null, contents, false);
    }

    @Override
    public String toString() {
        return "WrongAnswer(" + id + "," + questionNum + "," + contents + ")";
    }
}
