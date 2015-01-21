package doistres.werewolf;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class GameSetupConfirmationActivity extends ActionBarActivity {

    ArrayList classes_array = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Torna a Activity visível
        setContentView(R.layout.activity_game_setup_confirmation);

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView text = (TextView) findViewById(R.id.text_game_info);
        Button prox = (Button) findViewById(R.id.button_goToRandomClass);
        text.setTypeface(amatic);
        prox.setTypeface(amatic);


        // Recebe mensagem da Activity anterior (quantidade de jogadores e classes)
        Intent intent = getIntent();
        classes_array = intent.getStringArrayListExtra("classes");
        Collections.sort(classes_array);
        String quantity_s = intent.getStringExtra("quantity");

        // Atualiza TextViews da Activity
        TextView classes = (TextView) findViewById(R.id.text_classes_selected);
        TextView players = (TextView) findViewById(R.id.text_players_quantity);
        players.setText("Jogadores: "+ quantity_s);
        classes.setText("Classes: "+ ((CharSequence) classes_array.toString()));

        // Muda a fonte dos textos para AMATIC
        players.setTypeface(amatic);
        classes.setTypeface(amatic);


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

    // Vai para a prox Activity
    public void goToRandomClassActivity(View view){

        // Cria um intent da prox Activity
        Intent intent = new Intent(this, RandomClassActivity.class);

        // Envia o ArrayList de classes para a prox Activity
        intent.putExtra("classes", classes_array);

        // Inicia a prox Activity
        startActivity(intent);
    }
}
