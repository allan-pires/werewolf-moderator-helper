package doistres.werewolf;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

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

    // Vai para a prox Activity
    public void goToPlayersQuantityActivity(View view) {
        Intent intent = new Intent(this, PlayersQuantityActivity.class);
        startActivity(intent);
    }

    public void main(String[] args){
        Role lobisomem = new Role("Lobisomem", "Mate um camponês a cada noite");
        Role campones = new Role("Camponês", "Descubra quem é o lobisomem e ponha-o na fogueira!");
        Role vidente = new Role("Vidente", "Toda noite aponte para um jogador para saber se ele é lobisomem ou não");
        Role cupido = new Role("Cupido", "Escolha dois jogadores para serem amantes. Se um deles morrer, o outro morre de amor");
        Role cacador = new Role("Caçador", "Se for morto, não morra sozinho, aponte para algum jogador e leve-o com você");
        Role garotinha = new Role("Garotinha", "Pode espiar durante a noite, mas se for vista pelos lobisomens, morre de medo");
        Role bruxa = new Role("Bruxa", "Mate ou cure um jogador durante a noite, uma vez por jogo");
        Role lobisomem_alfa = new Role("Lobisomem Alfa", "Escolha um jogador para se transformar em Lobisomem");
        Role traidor = new Role("Traidor", "Ganhe se os Lobisomens ganharem");
        Role silenciador = new Role ("Silenciador", "Durante a noite, escolha um jogador para ficar calado no próximo dia");
    }
}
