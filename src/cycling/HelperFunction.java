
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.UUID;

public class HelperFunction {
    public ArrayList<String> getRacesNames(HashMap<Integer, Race> races) {
        ArrayList<String> racesNames = new ArrayList<>();
        for (Race race : races.values()) {
            racesNames.add(race.getName());
        }
        return racesNames;
    }

    public ArrayList<String> getTeamsNames(HashMap<Integer, Team> teams) {
        ArrayList<String> teamsNames = new ArrayList<>();
        for (Team team : teams.values()) {
            teamsNames.add(team.getTeamName());
        }
        return teamsNames;
    }

    public ArrayList<String> getStagesNames(HashMap<Integer, Race> races) {
        ArrayList<String> stagesNames = new ArrayList<>();
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                stagesNames.add(stage.getStageName());
            }
        }
        return stagesNames;
    }

    public static void main(String[] args) {
        HashMap<Integer, Race> races = new HashMap<Integer, Race>();
        races.put(1, new Race("F", "F"));
        HelperFunction hf = new HelperFunction();
        // hf.getRacesNames(races);
        System.out.println(hf.generateUniqueId());
    }

    public static Stage getStageByID(int stageID, HashMap<Integer, Race> races) {
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                if (stage.getStageID() == stageID) {
                    return stage;
                }
            }
        }
        return null;
    }

    public static int getRaceIDByStageID(int stageID, HashMap<Integer, Race> races) {
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                if (stage.getStageID() == stageID) {
                    return race.getRaceID();
                }
            }
        }
        return 0;
    }

    public static int getRaceIDByCheckpointID(int checkpointID, HashMap<Integer, Race> races) {
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                for (Checkpoint checkpoint : stage.getCheckpoints()) {
                    if (checkpoint.getCheckpointID() == checkpointID) {
                        return race.getRaceID();
                    }
                }
            }
        }
        return 0;
    }

    public static int getStageIDByCheckpointID(int checkpointID, HashMap<Integer, Race> races) {
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                for (Checkpoint checkpoint : stage.getCheckpoints()) {
                    if (checkpoint.getCheckpointID() == checkpointID) {
                        return stage.getStageID();
                    }
                }
            }
        }
        return 0;
    }

    public static ArrayList<Integer> getAllCheckpoints(HashMap<Integer, Race> races) {
        ArrayList<Integer> checkpoints = new ArrayList<>();
        for (Race race: races.values()) {
            for (Stage stage : race.getStages()) {
                for (Checkpoint checkpoint : stage.getCheckpoints()) {
                    checkpoints.add(checkpoint.getCheckpointID());
                
                }
            }
        }
        
        return checkpoints;
    }

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static ArrayList<Integer> getStagesIDs(HashMap<Integer, Race> races) {
        ArrayList<Integer> stagesIDs = new ArrayList<>();
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                stagesIDs.add(stage.getStageID());
            }
        }
        return stagesIDs;
    }

    public static ArrayList<Integer> getRidersIDs(HashMap<Integer, Team> teams) {
        ArrayList<Integer> ridersIDs = new ArrayList<>();
        for (Team team : teams.values()) {
            for (Rider rider : team.getRiders().values()) {
                ridersIDs.add(rider.getRiderID());
            }
        }
        return ridersIDs;
    }

    public int getTeamIDByRiderID(int riderID, HashMap<Integer, Team> teams){
        for (Team team : teams.values()){
            for (int riderIDtemp : team.getRiders().keySet()){
                if (riderIDtemp == riderID){
                    return team.getTeamID();
                }
            }
        }return 0;
    }

    
    

}
