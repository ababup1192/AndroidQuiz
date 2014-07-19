package org.ababup1192.quiz.quiz.game;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import org.ababup1192.quiz.database.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * データベース上の選択肢テーブル管理クラス
 */
public class AnswerRepository {

    private Context context;

    public AnswerRepository(Context context) {
        this.context = context;
    }

    /**
     * データベースから全ての選択肢を取得 (使わない)
     *
     * @return 正解(RightAnswer) or 不正解(WrongAnswer) クラスのインスタンスのリスト
     */
    public List<Answer> findAll() {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Answer, Integer> dao = helper.getDao(Answer.class);
            // Answer.getAnswerList により 選択肢 → 正解 or 不正解 クラスのインスタンスへ変換
            return Answer.getAnswerList(dao.queryForAll());
        } catch (Exception e) {
            Log.e("Quiz", "例外が発生しました", e);
            return null;
        } finally {
            helper.close();
        }
    }

    /**
     * 問題番号を指定して、選択肢を全て取得
     *
     * @param questionNum 問題番号
     * @return 正解(RightAnswer) or 不正解(WrongAnswer) クラスのインスタンスのリスト
     */
    public List<Answer> findByQuestionNum(Integer questionNum) {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Answer, Integer> dao = helper.getDao(Answer.class);
            // Answer.getAnswerList により 選択肢 → 正解 or 不正解 クラスのインスタンスへ変換
            return Answer.getAnswerList(dao.queryForEq("questionNum", questionNum));
        } catch (Exception e) {
            Log.e("Quiz", "例外が発生しました", e);
            return null;
        } finally {
            helper.close();
        }
    }

    /**
     * データベースに選択肢を保存
     *
     * @param questionNum 問題番号
     * @param answer      選択肢(answer) インスタンス
     * @throws SQLException データベースに例外が発生した場合は終了。
     */
    public void save(Integer questionNum, Answer answer) throws SQLException {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Answer, Integer> dao = helper.getDao(Answer.class);
            answer.setQuestionNum(questionNum);
            dao.createOrUpdate(answer).getNumLinesChanged();
        } catch (SQLException e) {
            Log.e("Quiz", "例外が発生しました", e);
            throw e;
        } finally {
            helper.close();
        }
    }


    public void save(Answer answer) throws SQLException {
        save(answer.getQuestionNum(), answer);
    }

    public void save(List<Answer> answerList) throws SQLException {
        for (Answer answer : answerList) {
            save(answer);
        }
    }

    /**
     * データベースに選択肢のリストを保存
     *
     * @param question   問題(Question)のインスタンス
     * @param answerList 選択肢(answer) インスタンスのリスト
     * @throws SQLException データベースに例外が発生した場合は終了。
     */
    public void save(Question question, List<Answer> answerList) throws SQLException {
        for (Answer answer : answerList) {
            save(question.getQuestionNum(), answer);
        }
    }

    /**
     * データベースから選択肢を全て削除
     */
    public void reset() {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Answer, Integer> dao = helper.getDao(Answer.class);
            TableUtils.dropTable(dao.getConnectionSource(), Answer.class, false);
            TableUtils.createTable(dao.getConnectionSource(), Answer.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
