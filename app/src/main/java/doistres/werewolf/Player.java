package doistres.werewolf;

/**
 * Created by doisl_000 on 21/01/2015.
 */
public class Player {

    Role role;
    Player bound;
    boolean love_bind;
    boolean dead;

    Player(){
        this.role= new Role();
        this.bound = new Player();
        this.love_bind = false;
        this.dead = false;
    }

    Player(Role role){
        this.role = role;
        this.dead = false;
    }

    public boolean isDead(){
        return this.dead;
    }

    public boolean isLoveBind(){
        return this.love_bind;
    }

}
