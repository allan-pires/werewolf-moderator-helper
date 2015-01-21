package doistres.werewolf;

/**
 * Created by doisl_000 on 21/01/2015.
 */
public class Role {

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

    public String getDescription(){
        return this.description;
    }

}
