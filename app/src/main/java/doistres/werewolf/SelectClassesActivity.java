package doistres.werewolf;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class SelectClassesActivity extends ActionBarActivity {

    MainActivity m = new MainActivity();
    // Mensagem a ser recebida
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Torna a Activity visível
        setContentView(R.layout.activity_select_class);

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView text = (TextView) findViewById(R.id.class_select_text);
        text.setTypeface(amatic);
        text = (TextView) findViewById(R.id.text_standart_classes);
        text.setTypeface(amatic);
        text = (TextView) findViewById(R.id.text_optional_classes);
        text.setTypeface(amatic);
        text = (TextView) findViewById(R.id.button_start);
        text.setTypeface(amatic);

        // Recebe mensagem da Activity anterior (quantidade de jogadores)
        Intent intent = getIntent();
        message = intent.getStringExtra("foo");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classpick, menu);
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

    // Cria um ArrayList com todas as classes selecionadas
    public ArrayList getSelectedClasses(){
        Role r = new Role();
        ArrayList<Role> selected_classes = new ArrayList<>();

        ToggleButton text = (ToggleButton) findViewById(R.id.toggle_lobisomem);
        if(text.isChecked()) selected_classes.add(r.createLobisomem());

        text = (ToggleButton) findViewById(R.id.toggle_campones);
        if(text.isChecked()) selected_classes.add(r.createCampones());

        text = (ToggleButton) findViewById(R.id.toggle_vidente);
        if(text.isChecked()) selected_classes.add(r.createVidente());

        text = (ToggleButton) findViewById(R.id.toggle_cupido);
        if(text.isChecked()) selected_classes.add(r.createCupido());

        text = (ToggleButton) findViewById(R.id.toggle_cacador);
        if(text.isChecked()) selected_classes.add(r.createCacador());

        text = (ToggleButton) findViewById(R.id.toggle_garotinha);
        if(text.isChecked()) selected_classes.add(r.createGarotinha());

        text = (ToggleButton) findViewById(R.id.toggle_bruxa);
        if(text.isChecked()) selected_classes.add(r.createBruxa());

        text = (ToggleButton) findViewById(R.id.toggle_lobisomem_alfa);
        if(text.isChecked()) selected_classes.add(r.createLobisomemAlfa());

        text = (ToggleButton) findViewById(R.id.toggle_traidor);
        if(text.isChecked()) selected_classes.add(r.createTraidor());

        text = (ToggleButton) findViewById(R.id.toggle_silenciador);
        if(text.isChecked()) selected_classes.add(r.createSilenciador());

        text = (ToggleButton) findViewById(R.id.toggle_mago);
        if(text.isChecked()) selected_classes.add(r.createMago());

        return selected_classes;
    }

    // Seta a quantidade de camponeses e lobisomens
    public ArrayList getSelectedClassesQuantity(){
        int quantity = Integer.parseInt(message);
        ArrayList<Role> classes = getSelectedClasses();
        Role r = new Role();

        if (quantity > 9){
            classes.add(r.createLobisomem());
        }

        for (int i = classes.size(); i < quantity; i++){
            classes.add(r.createCampones());
        }
        return classes;
    }

    // Vai para a prox Activity
    public void goToGameSetupConfirmationActivity(View view) {
        // Cria um intent da prox Activity
        Intent intent = new Intent(this, GameSetupConfirmationActivity.class);

        // Cria mensagem para enviar para prox Activity (classes selecionadas)
        ArrayList roles = getSelectedClassesQuantity();

        intent.putExtra("quantity", message);
        intent.putParcelableArrayListExtra("roles", roles);

        Button button = (Button) findViewById(R.id.button_start);
        button.setTextColor(Color.GRAY);

        // Inicia prox Activity
        startActivity(intent);
        this.finish();
    }

}
