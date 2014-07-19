package org.ababup1192.quiz.quiz.game;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.List;

/**
 * クイズゲームの管理クラス
 */
public class QuizGame {

    /**
     * sharedPreferences アプリの設定管理クラス
     * quizRepository クイズを取得するためのクラス
     */
    private SharedPreferences sharedPreferences;
    private QuizRepository quizRepository;

    public QuizGame(Context context) {
        quizRepository = new QuizRepository(context);
        sharedPreferences = context.getSharedPreferences("quiz", Context.MODE_PRIVATE);
    }

    public Integer getScore() {
        return sharedPreferences.getInt("score", 0);
    }

    public void setScore(int score) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }

    public Integer getHighScore() {
        return sharedPreferences.getInt("high_score", 0);
    }

    public void setHighScore(int score) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("high_score", score);
        editor.apply();
    }

    public Integer getCurrent() {
        return sharedPreferences.getInt("current", 0);
    }

    public void setCurrent(int current) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("current", current);
        editor.apply();
    }

    public Integer getLast() {
        return sharedPreferences.getInt("last", 0);
    }

    public void setLast(int last) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last", last);
        editor.apply();
    }

    public Integer getCurrentQuizNum() {
        return sharedPreferences.getInt(String.valueOf(getCurrent()), 1);
    }

    public Quiz getCurrentQuiz() {
        return quizRepository.findByQuestionNum(getCurrentQuizNum());
    }

    /**
     * ゲームの初期化・リセット
     */
    public void init() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // クイズリポジトリから全てのクイズを取得・シャッフル
        List<Quiz> quizList = quizRepository.findAll();
        Collections.shuffle(quizList);

        // スコア、１問目、最終出題数などを初期化
        setScore(0);
        setCurrent(1);
        setLast(quizList.size());

        for (int i = 0; i < quizList.size(); i++) {
            // 出題番号と問題番号の設定
            editor.putInt(String.valueOf(i + 1), quizList.get(i).getQuestion().getQuestionNum());
        }

        editor.apply();
    }

    /**
     * 次のゲームへの準備
     * @param isWin 正解の選択肢だったか否か
     */
    public void nextGame(Boolean isWin) {
        if (isWin) {
            // 正解だったらスコアと出題番号を次へ
            setScore(getScore() + 1);
            setCurrent(getCurrent() + 1);
        } else {
            // 不正解だったら出題番号のみを次へ
            setCurrent(getCurrent() + 1);
        }
    }

    public Boolean isLastGame() {
        return (getCurrent() - 1) == getLast();
    }

    public Boolean isHighScore() {
        return (getScore() > getHighScore());
    }

}
