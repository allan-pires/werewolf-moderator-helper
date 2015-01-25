package doistres.werewolf;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Role implements Parcelable, Comparable<Role> {

    // Nome da classe
    String name;

    // Descrição da classe
    String description;

    // Construtor padrão
    Role(){
        this.name = "";
        this.description="";
    }

    // Construtor com parâmetros
    Role(String name, String description){
        this.name = name;
        this.description = description;
    }

    // Comparador para fins de ordenar arrays
    public int compareTo(Role role){

        return this.name.compareTo(role.name);
    }

    // Resolve parcelas
    public Role (Parcel in){
        name = in.readString ();
        description = in.readString ();
    }

    // Resolve parcelas
    public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>(){
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
    public void writeToParcel (Parcel dest, int flags){
        dest.writeString (name);
        dest.writeString (description);
    }

    // Retorna descrição da classe
    public String getDescription(){
        return this.description;
    }

    // Cria a classe Lobisomem
    public Role createLobisomem(){
        return new Role(
                "Lobisomem", "Mate um camponês a cada noite");
    }

    // Cria a classe Camponês
    public Role createCampones(){
        return new Role("Camponês", "Descubra quem é o lobisomem e ponha-o na fogueira!");
    }

    // Cria a classe Vidente
    public Role createVidente(){
        return new Role("Vidente", "Toda noite aponte para um jogador para saber se ele é lobisomem ou não");
    }

    // Cria a classe Cupido
    public Role createCupido(){
        return new Role("Cupido", "Escolha dois jogadores para serem amantes. Se um deles morrer, o outro morre de amor");
    }

    // Cria a classe Caçador
    public Role createCacador(){
        return new Role("Caçador", "Se for morto, não morra sozinho, aponte para algum jogador e leve-o com você");
    }

    // Cria a classe Garotinha
    public Role createGarotinha(){
        return new Role("Garotinha", "Pode espiar durante a noite, mas se for vista pelos lobisomens, morre de medo");
    }

    // Cria a classe Bruxa
    public Role createBruxa(){
        return new Role("Bruxa", "Mate ou cure um jogador durante a noite, uma vez por jogo");
    }

    // Cria a classe Lobisomem Alfa
    public Role createLobisomemAlfa(){
        return new Role("Lobisomem Alfa", "Escolha um jogador para se transformar em Lobisomem");
    }

    // Cria a classe Traidor
    public Role createTraidor(){

        return new Role("Traidor", "Ganhe se os Lobisomens ganharem");
    }

    // Cria a classe Silenciador
    public Role createSilenciador(){
        return new Role ("Silenciador", "Durante a noite, escolha um jogador para ficar calado no próximo dia");
    }

    // Cria a classe Mago
    public Role createMago(){
        return new Role ("Mago", "Mate um jogador ou troque os lugares de jogadores durante a noite, modificando assim o alvo dos lobisomens");
    }


}
