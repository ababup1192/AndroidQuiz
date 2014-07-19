package org.ababup1192.quiz.quiz.game;

import java.util.List;

/**
 * クイズ = 問題 + 選択肢のリスト を表現しているクラス (HAS-A の関係)
 */
public class Quiz {
    private Question question;
    private List<Answer> answerList;

    public Quiz(Question question, List<Answer> answerList) {
        this.question = question;
        this.answerList = answerList;
    }

    public Question getQuestion(){
        return question;
    }

    public List<Answer> getAnswerList(){
        return answerList;
    }
}
