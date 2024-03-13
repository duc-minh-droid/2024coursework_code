import java.io.Serializable;
import java.util.HashMap;

public class Team implements Serializable{
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

    public HashMap<Integer, Rider> getRiders(){
        return riders;
    }

    public static int createTeam(String name, String description, HashMap<Integer, Team> teams ){
        //create a new team 
        Team team = new Team(name, description);

        //add the recent created team to teams system
        teams.put(team.getTeamID(), team);
        
        //return the Id of the recent created team
        return team.getTeamID();
    }

    public static int[] getTeams( HashMap<Integer, Team> teams ){
        //create an int array to store teamID
        int[] teamIds = new int[teams.size()];

        //create a counter
        int index = 0;

        //iterate through the key(teamID) in teams system and add them to array
        for (int teamID : teams.keySet()) {
            teamIds[index++] = teamID;
        }

         //return an array with teamId
        return teamIds;
    }

    public int[] getTeamRiders(){
        //create an array with size of number of riders within the team    
        int[] riderIDs = new int[riders.size()];

        //create a counter
        int index = 0;

        //iterate through all the rider in riders system and add their Id to the array
        for (int riderID : riders.keySet()) {
            riderIDs[index] = riderID;
            index++;
        }

        //return the the array with riders' Id
        return riderIDs;
    }

    public int createNewRider(int teamID, String riderName, int yearOfBirth){
        Rider rider = new Rider(teamID,riderName, yearOfBirth);
        riders.put(rider.getRiderID(), rider);
        return rider.getRiderID();
    }

     

    
}
