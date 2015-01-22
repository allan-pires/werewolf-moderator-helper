package doistres.werewolf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class GameSetupConfirmationActivity extends Activity {

    ArrayList<Role> classes_array = new ArrayList();

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


        // Recebe mensagem da Activity anterior (quantidade de jogadores e classes_turn)
        Intent intent = getIntent();
        ArrayList<Role> classes_array_parcelable = intent.getParcelableArrayListExtra("roles");
        classes_array = parcelLoad(classes_array_parcelable);
        String quantity_s = intent.getStringExtra("quantity");



        // Atualiza TextViews da Activity
        TextView classes = (TextView) findViewById(R.id.text_classes_selected);
        TextView players = (TextView) findViewById(R.id.text_players_quantity);
        players.setText("Jogadores: "+ quantity_s);
        String s = getRoleNames(classes_array);
        classes.setText("Classes: "+ s);

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

        // Envia o ArrayList de classes_turn para a prox Activity
        intent.putParcelableArrayListExtra("roles", classes_array);

        Button button = (Button) findViewById(R.id.button_goToRandomClass);
        button.setTextColor(Color.rgb(119, 1, 1));

        // Inicia a prox Activity
        startActivity(intent);
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

    // Cria um String só com os nomes das classes_turn
    public String getRoleNames(ArrayList<Role> classes_array) {
        String s = "";
        int campones_quantity = 0;
        int lobisomem_quantity = 0;

        // Conta a quantidade de camponeses
        for (int i = 0; i < classes_array.size(); i++) {
            if (classes_array.get(i).name.contentEquals("Camponês")) campones_quantity++;
            if (classes_array.get(i).name.contentEquals("Lobisomem")) lobisomem_quantity++;
        }

        s += "\n\t\t\tCamponês   x"+campones_quantity;
        s += "\n\t\t\tLobisomem   x"+lobisomem_quantity;
        for (int i = 0; i < classes_array.size(); i++) {
            String n = classes_array.get(i).name;
            if (!n.contentEquals("Lobisomem") && !n.contentEquals("Camponês")) s += "\n\t\t\t"+n+"   x1";
        }
        return s;
    }
}
