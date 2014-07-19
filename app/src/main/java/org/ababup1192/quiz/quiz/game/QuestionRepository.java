package org.ababup1192.quiz.quiz.game;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import org.ababup1192.quiz.database.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * データベース上の問題テーブル管理クラス
 */
public class QuestionRepository {

    private Context context;

    public QuestionRepository(Context context) {
        this.context = context;
    }

    /**
     * データベースから全ての問題を取得
     * @return 問題のリスト
     */
    public List<Question> findAll() {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Question, Integer> dao = helper.getDao(Question.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e("Quiz", "例外が発生しました", e);
            return null;
        } finally {
            helper.close();
        }
    }

    /**
     * 問題番号を指定して、問題を取得
     * @param questionNum 問題番号
     * @return 問題(Question) クラスのインスタンス
     */
    public Question findByQuestionNum(Integer questionNum) {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Question, Integer> dao = helper.getDao(Question.class);
            return dao.queryForEq("questionNum", questionNum).get(0);
        } catch (Exception e) {
            Log.e("Quiz", "例外が発生しました", e);
            return null;
        } finally {
            helper.close();
        }
    }

    /**
     * データベースに問題を保存
     * @param question 問題
     * @return 問題番号
     * @throws SQLException データベースに例外が発生した場合は終了。
     */
    public Integer save(Question question) throws SQLException {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Question, Integer> dao = helper.getDao(Question.class);
            return dao.createOrUpdate(question).getNumLinesChanged();
        } catch (SQLException e) {
            Log.e("Quiz", "例外が発生しました", e);
            throw e;
        } finally {
            helper.close();
        }
    }

    /**
     * データベースから問題を全て削除
     */
    public void reset() {
        DatabaseHelper helper = new DatabaseHelper(context);
        try {
            Dao<Question, Integer> dao = helper.getDao(Question.class);
            TableUtils.dropTable(dao.getConnectionSource(), Question.class, false);
            TableUtils.createTable(dao.getConnectionSource(), Question.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
