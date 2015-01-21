package doistres.werewolf;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by doisl_000 on 21/01/2015.
 */
public class Role implements Parcelable, Comparable<Role> {

    String name;
    String description;

    Role(){
        this.name = "";
        this.description="";
    }

    Role(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int compareTo(Role role){
        return this.name.compareTo(role.name);
    }

    public Role (Parcel in){
        name = in.readString ();
        description = in.readString ();
    }

    public String getDescription(){
        return this.description;
    }

    public Role createLobisomem(){
        return new Role("Lobisomem", "Mate um camponês a cada noite");
    }

    public Role createCampones(){
        return new Role("Camponês", "Descubra quem é o lobisomem e ponha-o na fogueira!");
    }

    public Role createVidente(){
        return new Role("Vidente", "Toda noite aponte para um jogador para saber se ele é lobisomem ou não");
    }

    public Role createCupido(){
        return new Role("Cupido", "Escolha dois jogadores para serem amantes. Se um deles morrer, o outro morre de amor");
    }

    public Role createCacador(){
        return new Role("Caçador", "Se for morto, não morra sozinho, aponte para algum jogador e leve-o com você");
    }

    public Role createGarotinha(){
        return new Role("Garotinha", "Pode espiar durante a noite, mas se for vista pelos lobisomens, morre de medo");
    }

    public Role createBruxa(){
        return new Role("Bruxa", "Mate ou cure um jogador durante a noite, uma vez por jogo");
    }

    public Role createLobisomemAlfa(){
        return new Role("Lobisomem Alfa", "Escolha um jogador para se transformar em Lobisomem");
    }

    public Role createTraidor(){
        return new Role("Traidor", "Ganhe se os Lobisomens ganharem");
    }

    public Role createSilenciador(){
        return new Role ("Silenciador", "Durante a noite, escolha um jogador para ficar calado no próximo dia");
    }

    public Role createMago(){
        return new Role ("Mago", "Mate um jogador ou troque os lugares de jogadores durante a noite, modificando assim o alvo dos lobisomens");
    }

    public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>()
    {
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    @Override
    public int describeContents ()
    {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags)
    {
        dest.writeString (name);
        dest.writeString (description);
    }


}
