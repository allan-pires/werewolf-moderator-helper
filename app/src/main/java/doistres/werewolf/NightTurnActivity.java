package doistres.werewolf;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class NightTurnActivity extends ActionBarActivity {

    boolean first_night = true;
    ArrayList<Role> classes_array = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_night_turn);

        // Recebe mensagem da Activity anterior (quantidade de jogadores)
        Intent intent = getIntent();
        ArrayList<Role> classes_array_parcelable = intent.getParcelableArrayListExtra("roles");
        for (int i = 0; i < classes_array_parcelable.size (); i++)
        {
            Role r = classes_array_parcelable.get(i);
            classes_array.add(r);
        }

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView t = (TextView) findViewById(R.id.night_turn);
        t.setTypeface(amatic);
        t = (TextView) findViewById(R.id.text_class_turn);
        t.setTypeface(amatic);
        t = (TextView) findViewById(R.id.button_next_class);
        t.setTypeface(amatic);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_night_turn, menu);
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

    public void getNextClass(){

    }
}
