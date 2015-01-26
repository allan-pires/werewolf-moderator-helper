package doistres.werewolf;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class GameActivity extends ActionBarActivity {

    // Indicador da primeira noite
    boolean first_night = true;

    // Contador de dias
    int day_count = 1;

    // Array com todas as Roles selecionadas
    ArrayList<Role> classes_array = new ArrayList();

    // Array com os nomes das classe que ainda faltam jogar
    ArrayList<String> classes_turn = new ArrayList<>();

    // Array de todos os jogadores
    ArrayList<Player> players_array = new ArrayList<>();

    // Array de todas as Views de jogadores
    ArrayList<ImageView> image_view_players_array =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deixa fullscreen e remove a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        // Recebe mensagem da Activity anterior (quantidade de jogadores e classes_turn)
        Intent intent = getIntent();
        ArrayList<Role> classes_array_parcelable = intent.getParcelableArrayListExtra("roles");
        classes_array = parcelLoad(classes_array_parcelable);
        classes_turn = getRoleNames(classes_array);
        classes_turn.remove("Camponês");

        // Muda a fonte dos textos para AMATIC
        Typeface amatic = Typeface.createFromAsset(getAssets(), "amatic.ttf");
        TextView t = (TextView) findViewById(R.id.day_night_text);
        t.setTypeface(amatic);
        t = (TextView) findViewById(R.id.text_class_turn);
        t.setTypeface(amatic);
        t = (TextView) findViewById(R.id.button_next_class);
        t.setTypeface(amatic);


        // Toca musiquinha
        MainActivity.mMediaPlayer.stop();
        MainActivity.mMediaPlayer = MediaPlayer.create(this, R.raw.horror_ambience);
        MainActivity.mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        MainActivity.mMediaPlayer.setLooping(true);
        MainActivity.mMediaPlayer.start();

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

    // Cria instância de um jogador
    public Player createPlayer(Role role){
        Player p1 = new Player(role);
        return p1;
    }

    // Cria instâncias de todos os jogadores
    public ArrayList<Player> createAllPlayers(){
        ArrayList<Player> a1 = new ArrayList<>();

        for (int i = 0; i < classes_array.size(); i++){
            Player p = createPlayer(classes_array.get(i));
            a1.add(p);
        }

        return a1;
    }

    // Cria as Views de jogador na tela
    public void createPlayerView(Player player){
        ImageView v1 = new ImageView(this);

        switch (player.role.name) {
            case "Lobisomem":
                v1.setImageResource(R.drawable.lobisomem_selected_xhdpi);
                break;
            case "Camponês":
                v1.setImageResource(R.drawable.campones_selected_xhdpi);
                break;
            case "Vidente":
                v1.setImageResource(R.drawable.vidente_selected_xhdpi);
                break;
            case "Cupido":
                v1.setImageResource(R.drawable.cupido_selected_xhdpi);
                break;
            case "Caçador":
                v1.setImageResource(R.drawable.garotinha_selected_xhdpi);
                break;
            case "Bruxa":
                v1.setImageResource(R.drawable.bruxa_selected_xhdpi);
                break;
            case "Lobisomem Alfa":
                v1.setImageResource(R.drawable.lobisomem_alfa_selected_xhdpi);
                break;
            case "Traidor":
                v1.setImageResource(R.drawable.traidor_selected_xhdpi);
                break;
            case "Silenciador":
                v1.setImageResource(R.drawable.silenciador_selected_xhdpi);
                break;
            case "Mago":
                v1.setImageResource(R.drawable.mago_selected_xhdpi);
                break;
        }


        int overflow = 4;
        if (image_view_players_array.size() < 4){
            LinearLayout ll = (LinearLayout) findViewById(R.id.image_view_players);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //rl.addRule(RelativeLayout.BELOW, R.id.day_night_text);
            v1.setPadding(25, 25, 0, 0);
            ll.addView(v1,lp);
        }
        else {
            LinearLayout ll = (LinearLayout) findViewById(R.id.image_view_players);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //rl.addRule(RelativeLayout.BELOW, R.id.day_night_text);
            v1.setPadding(25, 25, 0, 0);
            ll.addView(v1, lp);
        }

        image_view_players_array.add(v1);

    }

    // Mostra os jogadores na Activity durante o dia
    public void createAllPlayerViews(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++){
            createPlayerView(players.get(i));
        }
    }

    // Cria um Listener para uma ImageView
    public void createImageViewListener(ImageView v){
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                v.setAlpha((float)0.3);
            }

        });
    }

    // Cria Listener para todas as ImageView
    public void createAllImageListener(ArrayList<ImageView> v){
        for (int i = 0; i < v.size(); i++){
            createImageViewListener(v.get(i));
        }
    }

    // Retorna todas as classes ativas
    public ArrayList<String> getRoleNames(ArrayList<Role> roles){
        ArrayList<String> n = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++){
            if (!n.contains(roles.get(i).name)) n.add(roles.get(i).name);
        }
        return n;
    }

    // Retorna as classes ativas que possuem ações durante a noite
    public ArrayList<String> getActionRoleNames(ArrayList<Role> roles){
        ArrayList<String> x = getRoleNames(roles);
        ArrayList<String> n = new ArrayList<>();
        n.add("Lobisomem");
        n.add("Lobisomem Alfa");
        n.add("Vidente");
        n.add("Bruxa");
        n.add("Mago");
        n.add("Silenciador");

        //Interseção
        n.retainAll(x);
        return n;
    }

    // Manda acordar determinada classe
    public void wakeUp(String role){

        // Cria animacao de texto
        TextView text_role = (TextView) findViewById(R.id.text_class_turn);
        TextView action = (TextView) findViewById(R.id.text_class_objective);
        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Acorda personagem
        text_role.setText("Turno do(a) "+role);
        text_role.startAnimation(fadeIn);
        action.startAnimation(fadeIn);
        String message = "Peça para que o(a) "+role+" acorde";

        // Se for lobisomem
        if (role == "Lobisomem" && !first_night){
            message = "Peça para que os Lobisomens acordem e selecionem uma vítima";
        }

        if (role == "Lobisomem Alfa" && !first_night){
            message = "Pergunte se o Lobisomem alfa gostaria de transformar alguem em Lobisomem";
        }

        if (role == "Vidente" && !first_night){
            message = "Peça para que a Vidente acorde e descubra se um jogador é lobisomem";
        }

        if (role == "Cupido"){
            message = "Peça para que o Cupido crie um laço amoroso entre dois jogadores";
        }

        if (role == "Bruxa" && !first_night){
            message = "Peça para que a Bruxa acorde e pergunte se ela quer usar alguma de suas poções";
        }

        if (role == "Mago" && !first_night){
            message = "Peça para que a Vidente acorde e descubra se ele quer usar sua poção ou trocar o lugar de jogadores";
        }

        if (role == "Silenciador" && !first_night){
            message = "Peça para que o Silenciador roube a voz de um jogador";
        }

        action.setText(message);
    }

    // Passa para o turno de outra classe
    public void getNextClass(View view){
        Button b = (Button) findViewById(R.id.button_next_class);
        final Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        b.startAnimation(scale);
        if (classes_turn.contains("Camponês")) classes_turn.remove("Camponês");

        // Se ainda há classes_turn a serem chamadas
        if (!classes_turn.isEmpty()) {

            // Se for primeira noite, chama todas as classes_turn
            if(first_night){
                firstNightTurn();
            }

            // Senão, chama apenas as classes_turn com ações
            else {
                RelativeLayout bg = (RelativeLayout) findViewById(R.id.game_bg);
                bg.setBackgroundResource(R.drawable.bg_noite);
                nightTurn();
            }
        }

        // Se não há classes_turn a serem chamadas, amanhece o dia
        else {
            first_night = false;
            RelativeLayout bg = (RelativeLayout) findViewById(R.id.game_bg);
            bg.setBackgroundResource(R.drawable.bg_dia);
            morningTurn();
            day_count++;

            // Reseta classes
            classes_turn = getActionRoleNames(classes_array);
        }

    }

    // Inicia o turno matinal
    public void morningTurn(){

        // Toca musiquinha
        MainActivity.mMediaPlayer.stop();
        MainActivity.mMediaPlayer = MediaPlayer.create(this, R.raw.quiet_woodlands);
        MainActivity.mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        MainActivity.mMediaPlayer.setLooping(true);
        MainActivity.mMediaPlayer.start();
        MainActivity.mMediaPlayer.setVolume(50, 50);

        // Cria animacao de texto
        TextView text_role = (TextView) findViewById(R.id.text_class_turn);
        TextView action = (TextView) findViewById(R.id.text_class_objective);
        TextView text_day_night = (TextView) findViewById(R.id.day_night_text);
        Button next_class = (Button) findViewById(R.id.button_next_class);

        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Acorda personagem
        text_role.setText("Todos acordam");
        text_day_night.setText("Dia    "+day_count);
        action.setText("");
        next_class.setText("ANOITECER >");

        // Animações
        text_role.startAnimation(fadeIn);
        action.startAnimation(fadeIn);

        // Mostra jogadores
        players_array = createAllPlayers();
        createAllPlayerViews(players_array);
        createAllImageListener(image_view_players_array);
    }

    // Inicia o turno da noite
    public void nightTurn(){

        // Toca musiquinha
        MainActivity.mMediaPlayer.stop();
        MainActivity.mMediaPlayer = MediaPlayer.create(this, R.raw.horror_ambience);
        MainActivity.mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        MainActivity.mMediaPlayer.setLooping(true);
        MainActivity.mMediaPlayer.start();
        MainActivity.mMediaPlayer.setVolume(100, 100);

        // Inicia ponteiros
        TextView text_role = (TextView) findViewById(R.id.text_class_turn);
        TextView action = (TextView) findViewById(R.id.text_class_objective);
        TextView text_day_night = (TextView) findViewById(R.id.day_night_text);
        Button next_class = (Button) findViewById(R.id.button_next_class);

        // Atualiza ponteiros
        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Acorda personagem
        text_day_night.setText("Noite    "+day_count);
        text_role.setText("E assim... a noite cai");
        action.setText("");
        next_class.setText("PRÓXIMA CLASSE >");

        // Animações
        text_role.startAnimation(fadeIn);
        action.startAnimation(fadeIn);

        if (classes_turn.contains("Lobisomem")) {
            wakeUp("Lobisomem");
            classes_turn.remove("Lobisomem");
        }

        else if (classes_turn.contains("Lobisomem Alfa")) {
            wakeUp("Lobisomem Alfa");
            classes_turn.remove("Lobisomem Alfa");
        }

        else if (classes_turn.contains("Vidente")) {
            wakeUp("Vidente");
            classes_turn.remove("Vidente");
        }

        else if (classes_turn.contains("Bruxa")) {
            wakeUp("Bruxa");
            classes_turn.remove("Bruxa");
        }

        else if (classes_turn.contains("Mago")) {
            wakeUp("Mago");
            classes_turn.remove("Mago");
        }

        else if (classes_turn.contains("Silenciador")) {
            wakeUp("Silenciador");
            classes_turn.remove("Silenciador");
        }
    }

    // Inicia o jogo com o turno noturno, acordando todas as classes
    public void firstNightTurn(){

        if (classes_turn.contains("Lobisomem")) {
            wakeUp("Lobisomem");
            classes_turn.remove("Lobisomem");
        }

        else if (classes_turn.contains("Lobisomem Alfa")) {
            wakeUp("Lobisomem Alfa");
            classes_turn.remove("Lobisomem Alfa");
        }

        else if (classes_turn.contains("Vidente")) {
            wakeUp("Vidente");
            classes_turn.remove("Vidente");
        }

        else if (classes_turn.contains("Cupido")) {
            wakeUp("Cupido");
            classes_turn.remove("Cupido");
        }

        else if (classes_turn.contains("Caçador")) {
            wakeUp("Caçador");
            classes_turn.remove("Caçador");
        }

        else if (classes_turn.contains("Garotinha")) {
            wakeUp("Garotinha");
            classes_turn.remove("Garotinha");
        }

        else if (classes_turn.contains("Bruxa")) {
            wakeUp("Bruxa");
            classes_turn.remove("Bruxa");
        }

        else if (classes_turn.contains("Mago")) {
            wakeUp("Mago");
            classes_turn.remove("Mago");
        }

        else if (classes_turn.contains("Traidor")) {
            wakeUp("Traidor");
            classes_turn.remove("Traidor");
        }

        else if (classes_turn.contains("Silenciador")) {
            wakeUp("Silenciador");
            classes_turn.remove("Silenciador");
        }

    }

}
