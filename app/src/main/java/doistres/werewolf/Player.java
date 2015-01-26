package doistres.werewolf;

import android.widget.ImageView;

/**
 * Created by doisl_000 on 21/01/2015.
 */
public class Player {

    // Classe do jogador
    Role role;

    // Amante, caso o jogador tenha sido selecionado pelo cupido
    Player bound;

    // Indicador de ligação amorosa realizada pelo cupido
    boolean love_bind;

    // Indicador de morte
    boolean dead;

    ImageView img;

    // Construtor padrão
    Player(){
        this.role= new Role();
        this.bound = new Player();
        this.love_bind = false;
        this.dead = false;
    }

    // Construtor de roles
    Player(Role role){
        this.role = role;
        this.dead = false;
    }

    // Retorna se o jogador está morto
    public boolean isDead(){
        return this.dead;
    }

    // Retorna se o jogador tem um amante
    public boolean isLoveBind(){
        return this.love_bind;
    }

}
