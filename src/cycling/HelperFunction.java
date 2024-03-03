
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
//        hf.getRacesNames(races);
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

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    
}
