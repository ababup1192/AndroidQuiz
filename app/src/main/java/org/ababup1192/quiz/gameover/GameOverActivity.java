package org.ababup1192.quiz.gameover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ababup1192.quiz.R;
import org.ababup1192.quiz.quiz.game.QuizGame;
import org.ababup1192.quiz.quiz.activity.QuizActivity;
import org.ababup1192.quiz.title.TitleActivity;

public class GameOverActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Contextの取得
        final Context context = this;

        // クイズゲームの取得
        QuizGame quizGame = new QuizGame(this);

        // レイアウトの束縛
        LinearLayout scoreLayout = (LinearLayout) findViewById(R.id.layout_score);
        TextView continueText = (TextView) findViewById(R.id.text_continue);
        TextView endText = (TextView) findViewById(R.id.text_end);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // スコアの準備
        TextView scoreText = new TextView(this);
        scoreText.setText(quizGame.getScore().toString());
        scoreText.setTextSize(40);
        scoreText.setLayoutParams(layoutParams);

        // ハイスコアの準備
        TextView highScoreText = new TextView(this);
        highScoreText.setText(getString(R.string.game_over_high_score));
        highScoreText.setTextSize(40);
        highScoreText.setLayoutParams(layoutParams);

        // スコアの表示
        scoreLayout.addView(scoreText);
        // もしハイスコアだったら、「ハイスコア!!」と表示
        if (quizGame.isHighScore()) {
            quizGame.setHighScore(quizGame.getScore());
            scoreLayout.addView(highScoreText);
        }

        // クイズゲームのリセット
        new QuizGame(this).init();

        // 「つづく」を押した場合
        continueText.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(context, QuizActivity.class);
                                                startActivity(intent);
                                            }
                                        }
        );
        // 「おわり」を押した場合
        endText.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(context, TitleActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_over, menu);
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
