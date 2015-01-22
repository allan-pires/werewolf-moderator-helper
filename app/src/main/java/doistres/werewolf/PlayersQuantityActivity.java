package doistres.werewolf;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.TextView;


public class PlayersQuantityActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Torna a Activity visível
        setContentView(R.layout.activity_players_quantity);

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView text = (TextView) findViewById(R.id.text_players_quantity);
        text.setTypeface(amatic);
        Button button = (Button) findViewById(R.id.text_ok_players_quantity);
        button.setTypeface(amatic);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playerspick, menu);
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

    // Vai para a prox Activity
    public void goToSelectClassesActivity(View view) {

        EditText quantidade = (EditText) findViewById(R.id.players_quantity);

        // Se tiver inserido quantidade de jogador, continua, senão...
        if (!quantidade.getText().toString().isEmpty()) {
            int quantidade_int = Integer.parseInt(quantidade.getText().toString());

            // Se tiver mais de 4 jogadores, continua, senão...
            if (quantidade_int > 4) {

                if (quantidade_int <= 68) {
                    // Cria um intent da prox Activity
                    Intent intent = new Intent(this, SelectClassesActivity.class);

                    // Cria mensagem para enviar para prox Activity (quantidade de jogadores)

                    String message = quantidade.getText().toString();
                    intent.putExtra("foo", message);

                    Button button = (Button) findViewById(R.id.text_ok_players_quantity);
                    button.setTextColor(Color.rgb(119, 1, 1));

                    // Inicia prox Activity
                    startActivity(intent);

                }
                else new AlertDialog.Builder(this)
                        .setMessage("Tá exaltado, fera? O número máximo de jogadores é 68.")
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            // Msg de erro
            else new AlertDialog.Builder(this)
                    .setMessage("Precisa fazer novos amigos, hein? O número mínimo de jogadores é 5!")
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        // Msg de erro
        else new AlertDialog.Builder(this)
                .setMessage("Tá me zoando? Você precisa informar a quantidade de jogadores!")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
