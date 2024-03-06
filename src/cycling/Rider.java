import java.io.Serializable;

public class Rider implements Serializable{
    private int riderID;
    private int teamID;
    private String riderName;
    private int YearOfBirth;
    

    public Rider(int teamID, String riderName, int YearOfBirth) {
        HelperFunction hf = new HelperFunction();
        this.teamID = teamID;
        this.riderID = hf.generateUniqueId();
        this.riderName = riderName;
        this.YearOfBirth = YearOfBirth;
    }

    public int getRiderID(){
        return riderID;
    }

    public int getTeamID(){
        return teamID;
    }
}
