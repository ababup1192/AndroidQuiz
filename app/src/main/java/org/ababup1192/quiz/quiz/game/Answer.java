package org.ababup1192.quiz.quiz.game;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * クイズの選択肢、データベース保存用とRightAnswerとWrongAnswerのスーパークラス
 */
@DatabaseTable(tableName = "Answer")
public class Answer {

    /**
     * id 自動生成
     * questionNum  問題番号
     * contents 選択肢内容
     * right = 正解か否か
     */
    @DatabaseField(generatedId = true)
    protected Integer id;
    @DatabaseField
    protected Integer questionNum;
    @DatabaseField
    protected String contents;
    @DatabaseField
    protected Boolean right;

    public Answer() {
    }

    public Answer(Integer id, Integer questionNum, String contents, Boolean right) {
        this.id = id;
        this.questionNum = questionNum;
        this.contents = contents;
        this.right = right;
    }

    public Answer(Integer questionNum, String contents, Boolean right) {
        this.questionNum = questionNum;
        this.contents = contents;
        this.right = right;
    }


    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public String getContents() {
        return contents;
    }

    public Boolean getRight() {
        return right;
    }

    /**
     * 選択肢 → 正解(RightAnswer) or 不正解(WrongAnswer) クラスのインスタンスとして返す。
     *
     * @return RightAnswer or WrongAnswer
     */
    public Answer getAnswer() {
        if (right) {
            return new RightAnswer(id, questionNum, contents);
        } else {
            return new WrongAnswer(id, questionNum, contents);
        }
    }

    /**
     * 選択肢のリスト →  正解(RightAnswer) or 不正解(WrongAnswer) クラスのインスタンスのリストとして返す。
     *
     * @param list 選択肢(Answer)のリスト
     * @return 正解(RightAnswer) or 不正解(WrongAnswer) クラスのインスタンスのリスト
     */
    public static List<Answer> getAnswerList(List<Answer> list) {
        List<Answer> answersList = new ArrayList<Answer>();
        for (Answer answer : list) {
            answersList.add(answer.getAnswer());
        }
        return answersList;
    }

    @Override
    public String toString() {
        return "Answer(" + id + "," + questionNum + "," + contents + "," + right + ")";
    }
}
