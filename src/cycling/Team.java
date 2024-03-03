

public class Team {
    private int teamID;
    private String teamName;
    private String description;
    private int teamRiders;

    public Team(String teamName,String description, int teamRiders ){
        HelperFunction hf = new HelperFunction();
        this.teamID = hf.generateUniqueId();
        this.teamName = teamName;
        this.description = description;
        this.teamRiders = teamRiders;
    }
}
