package doistres.werewolf;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import static android.media.AudioManager.*;


public class MainActivity extends ActionBarActivity {

    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Torna a Activity visível
        setContentView(R.layout.activity_main);

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        Button button = (Button) findViewById(R.id.new_game_button);
        button.setTypeface(amatic);
        button = (Button) findViewById(R.id.options_button);
        button.setTypeface(amatic);
        button = (Button) findViewById(R.id.exit_button);
        button.setTypeface(amatic);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
        // This above line close correctly
    }

    // Vai para a prox Activity
    public void goToPlayersQuantityActivity(View view) {
        Intent intent = new Intent(this, PlayersQuantityActivity.class);

        Button button = (Button) findViewById(R.id.new_game_button);
        button.setTextColor(Color.rgb(119, 1, 1));

        startActivity(intent);
    }

    public void exitApp(View view){
        Button button = (Button) findViewById(R.id.exit_button);
        button.setTextColor(Color.rgb(119, 1, 1));

        finish();
        System.exit(0);
    }
}
