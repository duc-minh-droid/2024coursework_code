
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.UUID;
import java.time.LocalTime;
import java.time.Duration;
import java.util.*;

public class HelperFunction {
        public static HashMap<StageType, ArrayList<Integer>> stagePointsDistributionMatrix(){
        HashMap<StageType, ArrayList<Integer>> points = new HashMap<StageType, ArrayList<Integer>>();
        ArrayList<Integer> Fpoints = new ArrayList<Integer>(Arrays.asList(50, 30, 20, 18, 16, 14, 12, 10 ,8, 7, 6, 5, 4, 3, 2));
        ArrayList<Integer> Mpoints = new ArrayList<Integer>(Arrays.asList(30, 25, 22, 19 , 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2));
        ArrayList<Integer> Hpoints = new ArrayList<Integer>(Arrays.asList(20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        ArrayList<Integer> TTpoints = new ArrayList<Integer>(Arrays.asList(20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));

        points.put(StageType.FLAT, Fpoints);
        points.put(StageType.MEDIUM_MOUNTAIN, Mpoints);
        points.put(StageType.HIGH_MOUNTAIN, Hpoints);
        points.put(StageType.TT, TTpoints);
        
        return points;
    }

    public static int compareLocalTime(LocalTime time1, LocalTime time2) {
        // System.out.println(time1 + " compare to " + time2);
        return time1.compareTo(time2);
    }

    public static List<Integer> getSortedRiderIDs(Map<Integer, LocalTime> ridersElapsedTimes) {

        // Create a list of Map.Entry objects to store rider IDs and elapsed times
        List<Map.Entry<Integer, LocalTime>> entryList = new ArrayList<>(ridersElapsedTimes.entrySet());
        entryList.sort((entry1, entry2) -> compareLocalTime(entry1.getValue(), entry2.getValue()));
        // Extract the sorted rider IDs into a separate list
        List<Integer> sortedRiderIDs = new ArrayList<>();
        for (Map.Entry<Integer, LocalTime> entry : entryList) {
            sortedRiderIDs.add(entry.getKey());
        }

        return sortedRiderIDs;
    }

    public static int[] sortHashMapByValues(LinkedHashMap<Integer, Integer> map) {
        // Create a list of Map.Entry objects to store key-value pairs
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list based on values (descending order)
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Extract the sorted keys into an array
        int[] sortedKeys = new int[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            sortedKeys[i] = entryList.get(i).getKey();
        }

        return sortedKeys;
    }

    public static int retrieveStagePoint(int position, StageType type) {
        HashMap<StageType, ArrayList<Integer>> Spoints = stagePointsDistributionMatrix();
        return Spoints.get(type).get(position);
    }

    public static ArrayList<Integer> intermediateSprintPointsDistribution(){
     ArrayList<Integer> ISpoints = new ArrayList<Integer>(Arrays.asList(20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
     return ISpoints;
    }

    public static int retrieveSprintPoint(int position) {
        ArrayList<Integer> ISpoints = intermediateSprintPointsDistribution();
        return ISpoints.get(position);
        
    }

    public static HashMap<CheckpointType, ArrayList<Integer>> MountainPointsDistribution(){
        HashMap<CheckpointType, ArrayList<Integer>> Mpoints = new HashMap<CheckpointType, ArrayList<Integer>>();
        ArrayList<Integer> C4 = new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> C3 = new ArrayList<Integer>(Arrays.asList(2, 1, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> C2 = new ArrayList<Integer>(Arrays.asList(5, 3, 2, 1, 0, 0, 0, 0));
        ArrayList<Integer> C1 = new ArrayList<Integer>(Arrays.asList(10, 8, 6, 4, 2, 1, 0, 0));
        ArrayList<Integer> HC = new ArrayList<Integer>(Arrays.asList(20, 15, 12, 10, 8, 6, 4, 2));
        Mpoints.put(CheckpointType.C4, C4);
        Mpoints.put(CheckpointType.C3, C3);
        Mpoints.put(CheckpointType.C2, C2);
        Mpoints.put(CheckpointType.C1, C1);
        Mpoints.put(CheckpointType.HC, HC);
        return Mpoints;
    } 

    public static int retrieveMountainPoint(int position, CheckpointType type) {
        HashMap<CheckpointType, ArrayList<Integer>> Mpoints = MountainPointsDistribution();
        return Mpoints.get(type).get(position);
    }

    public static LocalTime getElapsedTime(LocalTime startTime, LocalTime endTime) {
        // Calculate duration between the two times
        Duration duration = Duration.between(startTime, endTime);

        // Extract hours, minutes, seconds, and nanoseconds from the duration
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long nanos = duration.toNanosPart();

        // Create a new LocalTime object representing the elapsed time
        return LocalTime.ofNanoOfDay(hours * 3600_000_000_000L + minutes * 60_000_000_000L + seconds * 1_000_000_000L + nanos);
    }

    public static ArrayList<String> getRacesNames(HashMap<Integer, Race> races) {
        ArrayList<String> racesNames = new ArrayList<>();
        for (Race race : races.values()) {
            racesNames.add(race.getName());
        }
        return racesNames;
    }

    public static ArrayList<String> getTeamsNames(HashMap<Integer, Team> teams) {
        ArrayList<String> teamsNames = new ArrayList<>();
        for (Team team : teams.values()) {
            teamsNames.add(team.getTeamName());
        }
        return teamsNames;
    }

    public static ArrayList<String> getStagesNames(HashMap<Integer, Race> races) {
        ArrayList<String> stagesNames = new ArrayList<>();
        for (Race race : races.values()) {
            for (Stage stage : race.getStages()) {
                stagesNames.add(stage.getStageName());
            }
        }
        return stagesNames;
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
                    return race.getRaceId();
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
                        return race.getRaceId();
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

    public static int getTeamIDByRiderID(int riderID, HashMap<Integer, Team> teams){
        for (Team team : teams.values()){
            for (int riderIDtemp : team.getRiders().keySet()){
                if (riderIDtemp == riderID){
                    return team.getTeamID();
                }
            }
        }return 0;
    }

    public static Rider getRiderByID(int riderID, HashMap<Integer, Team> teams) {
        for (Team team : teams.values()) {
            for (Rider rider : team.getRiders().values()) {
                if (rider.getRiderID() == riderID) {
                    return rider;
                }
            }
        }
        return null;
    }

    public static LocalTime sumTwoLocalTime(LocalTime time1, LocalTime time2) {
        return time1.plusHours(time2.getHour()).plusMinutes(time2.getMinute()).plusSeconds(time2.getSecond()).plusNanos(time2.getNano());
    }

}
