package org.ababup1192.quiz.quiz.game;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * クイズを取得するためのクラス
 */
public class QuizRepository {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuizRepository(Context context) {
        questionRepository = new QuestionRepository(context);
        answerRepository = new AnswerRepository(context);
    }

    /**
     * 全てのクイズを取得
     *
     * @return クイズのリスト
     */
    public List<Quiz> findAll() {
        //　クイズのリストの初期化
        List<Quiz> quizList = new ArrayList<Quiz>();

        // 全ての問題を取得
        for (Question question : questionRepository.findAll()) {
            // 問題番号を指定して、選択肢のリストを取得
            quizList.add(new Quiz(question, answerRepository.findByQuestionNum(question.getQuestionNum())));
        }
        return quizList;
    }

    /**
     * 問題番号を指定して、クイズを取得
     *
     * @param questionNum 問題番号
     * @return クイズ(Quiz)
     */
    public Quiz findByQuestionNum(Integer questionNum) {
        Question question = questionRepository.findByQuestionNum(questionNum);
        List<Answer> answerList = answerRepository.findByQuestionNum(questionNum);
        return new Quiz(question, answerList);
    }

    /**
     * クイズの登録
     *
     * @param questionContents 問題の内容
     * @param answerContents   選択肢の内容(可変長)
     * @throws SQLException データベースに例外が発生した場合は終了。
     */
    public void registerQuiz(String questionContents, String... answerContents) throws SQLException {
        Question question = new Question(questionContents);

        List<Answer> answerList = new ArrayList<Answer>();
        // 最初の選択肢は、正解の選択肢として登録
        answerList.add(new RightAnswer(answerContents[0]));

        // 2個目以降の選択肢は、不正解の選択肢として登録
        for (int i = 1; i < answerContents.length; i++) {
            answerList.add(new WrongAnswer(answerContents[i]));
        }
        // データベースへ保存
        save(question, answerList);
    }

    /**
     * @param question 問題(Question)
     * @param answer   選択肢(Answer)のリスト
     * @throws SQLException データベースに例外が発生した場合は終了。
     */
    public void save(Question question, List<Answer> answer) throws SQLException {
        questionRepository.save(question);
        answerRepository.save(question, answer);
    }

    /**
     * データベースから問題と選択肢を削除
     */
    public void reset() {
        answerRepository.reset();
        questionRepository.reset();
    }

}
