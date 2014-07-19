package org.ababup1192.quiz.application;

import org.ababup1192.quiz.quiz.game.QuizGame;
import org.ababup1192.quiz.quiz.game.QuizRepository;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // クイズデータベースの用意
        QuizRepository quizRepository = new QuizRepository(this);

        try {
            // アプリ起動時に、データベース初期化
            quizRepository.reset();

            // クイズの追加
            quizRepository.registerQuiz("クイズ1", "正解", "不正解1", "不正解2", "不正解3");
            quizRepository.registerQuiz("クイズ2", "正解", "不正解1", "不正解2", "不正解3");
            quizRepository.registerQuiz("クイズ3", "正解", "不正解1", "不正解2", "不正解3");
            quizRepository.registerQuiz("クイズ4", "正解", "不正解1", "不正解2", "不正解3");
            quizRepository.registerQuiz("クイズ5", "正解", "不正解1", "不正解2", "不正解3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // クイズゲームの初期化
        new QuizGame(this).init();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}