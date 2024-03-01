
import java.util.ArrayList;
import java.util.HashMap;

public class HelperFunction {
    public ArrayList<String> getRacesNames(HashMap<Integer, Race> races) {
        ArrayList<String> racesNames = new ArrayList<>();
        for (Race race : races.values()) {
            racesNames.add(race.getName());
        }
        return racesNames;
    }

    public static void main(String[] args) {
        HashMap<Integer, Race> races = new HashMap<Integer, Race>();
        races.put(1, new Race("F", "F"));
        HelperFunction hf = new HelperFunction();
        hf.getRacesNames(races);
    }
}
