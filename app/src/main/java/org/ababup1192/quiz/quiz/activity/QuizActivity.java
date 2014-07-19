package org.ababup1192.quiz.quiz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.ababup1192.quiz.R;
import org.ababup1192.quiz.gameover.GameOverActivity;
import org.ababup1192.quiz.quiz.game.Answer;
import org.ababup1192.quiz.quiz.game.Quiz;
import org.ababup1192.quiz.quiz.game.QuizGame;
import org.ababup1192.quiz.quiz.game.RightAnswer;

import java.util.Collections;
import java.util.List;

import util.LayoutHelper;

public class QuizActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Contextの取得
        final Context context = this;

        // クイズゲームの開始
        final QuizGame quizGame = new QuizGame(this);

        // レイアウトの束縛
        GridLayout gridLayout = (GridLayout) findViewById(R.id.layout_grid);
        TextView quizTitleText = (TextView) findViewById(R.id.text_quiz_title);
        TextView quizContentsText = (TextView) findViewById(R.id.text_quiz_contents);

        // 表示するクイズの取得
        Quiz quiz = quizGame.getCurrentQuiz();

        // 何問目かを表示
        quizTitleText.setText("第" + quizGame.getCurrent() + "問");
        // クイズの表示
        quizContentsText.setText(quiz.getQuestion().getContents());

        // 選択肢の取得とシャッフル
        List<Answer> answerList = quiz.getAnswerList();
        Collections.shuffle(answerList);

        // 選択肢をボタンとして生成し、レイアウトに追加
        for (Answer answer : answerList) {
            Button answerButton = new Button(this);
            answerButton.setText(answer.getContents());
            LayoutHelper.setGridLayoutParams(answerButton,
                    (int) getResources().getDimension(R.dimen.answer_button_width), ViewGroup.LayoutParams.WRAP_CONTENT);
            gridLayout.addView(answerButton);

            // 選択肢が正解だった場合
            if (answer instanceof RightAnswer) {
                answerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // スコアを増やして、次のクイズへ
                        quizGame.nextGame(true);

                        // もし、ラスト問題だったら終了、もしくは続行
                        if (quizGame.isLastGame()) {
                            Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                            context.startActivity(intent);
                        }
                    }
                });
                // 選択肢が不正回だったら
            } else {
                answerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // スコアは増やさず、次のクイズへ
                        quizGame.nextGame(false);

                        if (quizGame.isLastGame()) {
                            Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                            context.startActivity(intent);
                        }
                    }
                });
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
