import java.util.HashMap;

public class Team {
    private int teamID;
    private String teamName;
    private String description;
    private int teamRiders;
    private HashMap<Integer, Rider> riders = new HashMap<Integer, Rider>();

    public Team(String teamName,String description){
        HelperFunction hf = new HelperFunction();
        this.teamID = hf.generateUniqueId();
        this.teamName = teamName;
        this.description = description;
        this.teamRiders = teamRiders;
    }

    public String getTeamName(){
        return teamName;
    }

    public int getTeamID(){
        return teamID;
    }

    public int createNewRider(int teamID, String riderName, int yearOfBirth){
    Rider rider = new Rider(teamID,riderName, yearOfBirth);
        riders.put(rider.getRiderID(), rider);
        return rider.getRiderID();
    }

    public HashMap<Integer, Rider> getRiders(){
        return riders;
    }
}
