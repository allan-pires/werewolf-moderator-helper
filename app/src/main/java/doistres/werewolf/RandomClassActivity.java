package doistres.werewolf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class RandomClassActivity extends ActionBarActivity {
    ArrayList<Role> classes_array = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Torna a Activity visível
        setContentView(R.layout.activity_random_class);

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView text = (TextView) findViewById(R.id.text_player_class);
        Button prox = (Button) findViewById(R.id.button_select_class);
        text.setTypeface(amatic);
        prox.setTypeface(amatic);
        prox = (Button) findViewById(R.id.button_goToNightTurn);
        prox.setTypeface(amatic);

        // Recebe mensagem da Activity anterior (quantidade de jogadores)
        Intent intent = getIntent();
        ArrayList<Role> classes_array_parcelable = intent.getParcelableArrayListExtra("roles");
        for (int i = 0; i < classes_array_parcelable.size (); i++)
        {
            Role r = classes_array_parcelable.get(i);
            classes_array.add(r);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_class, menu);
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

    // Pega uma classe aleatória
    public void getRandomClass(View view){
        if (!classes_array.isEmpty()) {
            // Seleciona uma classe aleatória
            Collections.shuffle(classes_array);
            String random_class = classes_array.get(0).name;
            String random_class_discription = classes_array.get(0).description;
            random_class = random_class.toUpperCase();

            String description = "This method has two variants. First variant converts all of the characters in this String to upper case using the rules of the given Locale. This is equivalent to calling toUpperCase(Locale.getDefault()). ";


            // Atualiza texto de classe
            //TextView text = (TextView) findViewById(R.id.text_random_class);
            //text.setText(random_class);
            new AlertDialog.Builder(this)
                    .setMessage("Você é o(a): "+random_class+"\n\nDescrição: "+random_class_discription+"\n\nPor favor, aperte OK e passe o celular para o próximo jogador")
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            // Remove classe do array
            classes_array.remove(0);
        }
        else{

            // Mostra o botao para a prox Activity
            Button button = (Button) findViewById(R.id.button_goToNightTurn);
            button.setAlpha(1);
            button.setClickable(true);

            new AlertDialog.Builder(this)
                    .setMessage("Todas as classes já foram escolhidas!")
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    // Vai para a prox Activity
    public void goToNightTurnActivity(View view) {
        // Cria um intent da prox Activity
        Intent intent = new Intent(this, NightTurnActivity.class);

        intent.putParcelableArrayListExtra("roles", classes_array);

        // Inicia prox Activity
        startActivity(intent);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
