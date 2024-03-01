
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Iterator;

public class HelperFunction {
    public ArrayList<String> getRacesNames(HashMap<Integer, Race> races) {
        ArrayList<String> racesNames = new ArrayList<>();
        for (Race race : races.values()) {
            racesNames.add(race.getName());
        }
        return racesNames;
    }

    public int[] convertToIntArray(List<Integer> integerList) {
        int[] result = new int[integerList.size()];
        Iterator iterator = integerList.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) iterator.next();
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<Integer, Race> races = new HashMap<Integer, Race>();
        races.put(1, new Race("F", "F"));
        HelperFunction hf = new HelperFunction();
        hf.getRacesNames(races);
    }
}
