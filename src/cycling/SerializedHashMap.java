import java.util.HashMap;
import java.io.Serializable;

public class SerializedHashMap implements Serializable {
    private HashMap<Integer, Race> races;
    private HashMap<Integer, Team> teams;


    public SerializedHashMap( HashMap<Integer, Race> races,HashMap<Integer, Team> teams ){
        this.races = races;
        this.teams = teams;
    }

    public HashMap<Integer, Race> getRaces(){
        return races;
    }
    
    public HashMap<Integer, Team> getTeams(){
        return teams;
    }
}
