package doistres.werewolf;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class NightTurnActivity extends ActionBarActivity {

    boolean first_night = true;
    ArrayList<Role> classes_array = new ArrayList();
    ArrayList<Role> classes_next_turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_night_turn);

        // Recebe mensagem da Activity anterior (quantidade de jogadores)
        Intent intent = getIntent();
        ArrayList<Role> classes_array_parcelable = intent.getParcelableArrayListExtra("roles");
        classes_array = parcelLoad(classes_array_parcelable);
        classes_next_turn = new ArrayList<>(classes_array);

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

    // Resolve as parcelas do pacote extra
    public ArrayList<Role> parcelLoad(ArrayList<Role> array_parcelable){
        ArrayList<Role> array = new ArrayList<>();
        for (int i = 0; i < array_parcelable.size (); i++)
        {
            Role r = array_parcelable.get(i);
            array.add(r);
        }
        Collections.sort(array);
        return array;
    }

    public void getNextClass(View view){

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        Button button1 = (Button) findViewById(R.id.button_next_class);
        button1.startAnimation(animScale);

        Role r = new Role();
        String message = "";
        TextView text_class = (TextView) findViewById(R.id.text_class_turn);
        TextView text = (TextView) findViewById(R.id.text_class_objective);

        String s = classes_array.toString();
        text_class.setText(s);

        // Turno do lobisomem
        Role lobisomem = r.createLobisomem();
        if (classes_next_turn.contains(lobisomem)){
            if (first_night) {
                message = "Peça para que os lobisomens acordem e se conheçam";
                text.setText(message);
                text_class.setText("Turno do Lobisomem");
            }
            else {
                message = "Peça para que os lobisomens acordem e escolham uma vítima";
                text.setText(message);
                text_class.setText("Turno do Lobisomem");
            }
            classes_next_turn.remove(lobisomem);
        }
    }
}
