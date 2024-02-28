package cycling;

public class Team {
    private int teamID;
    private String teamName;
    private String description;
    private int teamRiders;

    public Team( int teamID, String teamName,String description, int teamRiders ){
        this.teamID = teamID;
        this.teamName = teamName;
        this.description = description;
        this.teamRiders = teamRiders;
    }
}
