package org.ababup1192.quiz.title;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ababup1192.quiz.R;
import org.ababup1192.quiz.quiz.activity.QuizActivity;
import org.ababup1192.quiz.quiz.game.QuizGame;

public class TitleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        // クイズゲームの取得
        QuizGame quizGame = new QuizGame(this);

        // レイアウトウィジェットの束縛
        TextView highScoreText = (TextView) findViewById(R.id.text_highScore);
        Button startButton = (Button) findViewById(R.id.button_start);

        // ハイスコアを表示
        highScoreText.setText(getString(R.string.high_score) + " " + quizGame.getHighScore());

        // スタートボタン タイトル → クイズ開始
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title, menu);
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
