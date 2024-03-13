import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Collections;

import java.util.LinkedHashMap;

public class Race implements Serializable {
    private final int raceId;
    private String name;
    private String description;
    private double length;
    private ArrayList<Stage> stages = new ArrayList<Stage>();
    private LinkedHashMap<Integer, Integer> ridersTotalMountainPointsInRace = new LinkedHashMap<Integer, Integer>();
    private LinkedHashMap<Integer, Integer> ridersTotalPointsInRace = new LinkedHashMap<Integer, Integer>();

    public Race(String name, String description) {
        
        this.name = name;
        this.description = description;
        this.raceId = HelperFunction.generateUniqueId();
    }

    public LinkedHashMap<Integer, Integer> getRidersTotalMountainPointsInRace() {
        return ridersTotalMountainPointsInRace;
    }
    public LinkedHashMap<Integer, Integer> getRidersTotalPointsInRace() {
        return ridersTotalPointsInRace;
    }
    public void putRidersTotalMountainPointsInRace(int id, int points) {
         ridersTotalMountainPointsInRace.put(id, points);
    }
    public void putRidersTotalPointsInRace(int id, int points) {
         ridersTotalPointsInRace.put(id, points);
    }
    public int getRaceId(){
        return raceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLength(){
        return length;
    }
    public ArrayList<Stage> getStages() {
        return stages;
    }
    

    public static int[] getRaceIds( HashMap<Integer, Race> races) {
        //create an int array to store raceID
        int[] raceIds = new int[races.size()];
        
        //create a counter
        int index = 0;
        
        //iterate through the key(raceID) in races system and add them to array
        for (int raceID : races.keySet()) {
            raceIds[index++] = raceID;
        }

        //return an array with raceId
        return raceIds;
    }


    public static int createRace(String name, String description,  HashMap<Integer, Race> races ){
        Race race = new Race(name, description);

        //add the created race to races system
        races.put(race.getRaceId(), race);

        //return the Id of the recent created race
        return race.getRaceId();
    }

   
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) {
        //create a new stage
        Stage stage = new Stage(raceId, stageName, description, length, startTime, type);
        
        //add the created stage to the stages system
        stages.add(stage);

        //return the stageId of the recent created stage
        return stage.getStageID();    
    }

    public int getNumberOfStages(){
        //return the number of created stages within the race
        return stages.size();
    }

    public int[] getRaceStages(){
        //create an array with size of number of stages of the race
        int[] raceStageIDs = new int[stages.size()];

        //create a counter
        int index = 0;

        //iterate through all the stage in stages system and add their Id to the array
        for (Stage stage : stages) {
            raceStageIDs[index] = stage.getStageID();
            index++;
        }

        //return the the array with stages' Id
        return raceStageIDs;
    }
    public int[] getRidersMountainPointClassificationRank() {
        LinkedHashMap<Integer, Integer> ridersTotalPointsInRace = getRidersTotalMountainPointsInRace();
        int[] sortedKeys = HelperFunction.sortHashMapByValues(ridersTotalPointsInRace);
        return sortedKeys;
    }

    public int[] getRidersPointClassificationRank() {
        LinkedHashMap<Integer, Integer> ridersTotalPointsInRace = getRidersTotalPointsInRace();
        int[] sortedKeys = HelperFunction.sortHashMapByValues(ridersTotalPointsInRace);
        return sortedKeys;
    }
    public int[] getRidersGeneralClassificationRank() {
        
        HashMap<Integer, LocalTime> riderTotalAdjustedElapsedTimes = new HashMap<Integer, LocalTime>();

        // Loop through each stage and find the accumulated Adjusted Elapsed Times for each rider
        for (Stage stage : stages) {
            for (int riderID : stage.getRidersAdjustedElapsedTimes().keySet()) {
                // Return an empty array if there is no result for any stage in the race 
                for (int riderIDcheck : riderTotalAdjustedElapsedTimes.keySet()) {
                    if (!stage.getRidersAdjustedElapsedTimes().containsKey(riderIDcheck)) {
                        int[] emptyArray = {};
                        return emptyArray;
                    }
                }
                if (riderTotalAdjustedElapsedTimes.containsKey(riderID)) {
                    riderTotalAdjustedElapsedTimes.put(riderID, HelperFunction.sumTwoLocalTime(riderTotalAdjustedElapsedTimes.get(riderID), stage.getRidersAdjustedElapsedTimes().get(riderID)));
                } else {
                    riderTotalAdjustedElapsedTimes.put(riderID, stage.getRidersAdjustedElapsedTimes().get(riderID));
                }
            }
        }

        // Sort the entries in descending order of LocalTime values
        ArrayList<Map.Entry<Integer, LocalTime>> sortedEntries = new ArrayList<>(riderTotalAdjustedElapsedTimes.entrySet());
        Collections.sort(sortedEntries, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

        // Extract the keys into the GCrank array
        int[] GCrank = new int[sortedEntries.size()];
        for (int i = 0; i < sortedEntries.size(); i++) {
            GCrank[i] = sortedEntries.get(i).getKey();
        }

        return GCrank;
    }

    public int[] getRidersPointsInRace() {
        int[] ridersRaceTotalPoints = {};

        // Loop through each rider ID in GC rank and add up their total point of each stage
        for(int riderID : getRidersGeneralClassificationRank()){ 
            int totalRiderPoints = 0;
            for (Stage stage : stages){
                HashMap<Integer, Integer> ridersTotalPointsInStage = stage.getRidersTotalPointsInStage();
                if (ridersTotalPointsInStage.keySet().contains(riderID)){
                    totalRiderPoints += ridersTotalPointsInStage.get(riderID);               
                }else {
                    int[] emptyArray = {};
                    return emptyArray;
                 }
                } 
            
            // Add element to the returning array
        ridersRaceTotalPoints = Arrays.copyOf(ridersRaceTotalPoints, ridersRaceTotalPoints.length + 1);
        ridersRaceTotalPoints[ridersRaceTotalPoints.length -1 ] = totalRiderPoints;
        putRidersTotalPointsInRace(riderID, totalRiderPoints);
        }
        return ridersRaceTotalPoints;    
    }   

    public int[] getRidersMountainPointsInRace() {
        int[] ridersRaceTotalMountainPoints = {};

        // Loop through each rider ID in GC rank and add up their total point of each stage
        for(int riderID : getRidersGeneralClassificationRank()){

            int totalRiderPoints = 0;
            for (Stage stage : stages){
                HashMap<Integer, Integer> ridersTotalMountainPointsInStage = stage.getRidersTotalMountainPointsInStage();
                    if (ridersTotalMountainPointsInStage.containsKey(riderID)){
                        totalRiderPoints += ridersTotalMountainPointsInStage.get(riderID);        
                    }else {
                        int[] emptyArray = {};
                        return emptyArray;
                    }
            }
            // Add element to the returning array
            ridersRaceTotalMountainPoints = Arrays.copyOf(ridersRaceTotalMountainPoints, ridersRaceTotalMountainPoints.length + 1);
            ridersRaceTotalMountainPoints[ridersRaceTotalMountainPoints.length -1 ] = totalRiderPoints;
            putRidersTotalMountainPointsInRace(riderID, totalRiderPoints);
            
        }
        return ridersRaceTotalMountainPoints;  
    }

    public LocalTime[] getGeneralClassificationTimesInRace() {
        
        HashMap<Integer, LocalTime> riderTotalAdjustedElapsedTimes = new HashMap<Integer, LocalTime>();

        // Loop through each stage and find the accumulated Adjusted Elapsed Times for each rider
        for (Stage stage : stages) {
            for (int riderID : stage.getRidersAdjustedElapsedTimes().keySet()) {
                // Return an empty array if there is no result for any stage in the race 
                for (int riderIDcheck : riderTotalAdjustedElapsedTimes.keySet()) {
                    if (!stage.getRidersAdjustedElapsedTimes().containsKey(riderIDcheck)) {
                        LocalTime[] emptyArray = {};
                        return emptyArray;
                    }
                }
                if (riderTotalAdjustedElapsedTimes.containsKey(riderID)) {
                    riderTotalAdjustedElapsedTimes.put(riderID, HelperFunction.sumTwoLocalTime(riderTotalAdjustedElapsedTimes.get(riderID), stage.getRidersAdjustedElapsedTimes().get(riderID)));
                } else {
                    riderTotalAdjustedElapsedTimes.put(riderID, stage.getRidersAdjustedElapsedTimes().get(riderID));
                }
            }
        }

        // Sort the entries in descending order of LocalTime values
        ArrayList<Map.Entry<Integer, LocalTime>> sortedEntries = new ArrayList<>(riderTotalAdjustedElapsedTimes.entrySet());
        Collections.sort(sortedEntries, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

        // Extract the keys into the GCrank array
        LocalTime[] GCtimes = new LocalTime[sortedEntries.size()];
        for (int i = 0; i < sortedEntries.size(); i++) {
            GCtimes[i] = sortedEntries.get(i).getValue();
        }
        return GCtimes;
    }
}
   
