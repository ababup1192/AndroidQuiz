package org.ababup1192.quiz.quiz.game;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 問題
 */
@DatabaseTable(tableName = "Question")
public class Question {

    /**
     * questionNum 問題番号
     * contents 問題の内容
     */
    @DatabaseField(generatedId = true)
    private Integer questionNum;
    @DatabaseField
    private String contents;

    public Question() {
    }

    public Question(String contents) {
        this.questionNum = questionNum;
        this.contents = contents;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Question(" + questionNum + "," + contents + ")";
    }

}
