import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
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
        HelperFunction hf = new HelperFunction();
        this.name = name;
        this.description = description;
        this.raceId = hf.generateUniqueId();
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
    // public int[] getRidersMountainPointsInRace() {
    //     int[] ridersRaceTotalMountainPoints = new int[getRidersGeneralClassificationRank(raceId).length];

    //     int totalRiderPoints = 0;
    //     // Loop through each rider ID in GC rank and add up their total point of each stage
    //     for(int riderID : getRidersGeneralClassificationRank(raceId)){ 
    //         for (Stage stage : stages){
    //             HashMap<Integer, Integer> ridersTotalMountainPointsInStage = stage.getridersTotalMountainPointsInStage();
    //             if (ridersTotalMountainPointsInStage.keySet().contains(riderID)){
    //                 totalRiderPoints += ridersTotalMountainPointsInStage.get(riderID);               
    //             }else {
    //                 int[] emptyArray = {};
    //                 return emptyArray;
    //             }
    //         ridersRaceTotalMountainPoints = Arrays.copyOf(ridersRaceTotalMountainPoints, ridersRaceTotalMountainPoints.length + 1);
    //         ridersRaceTotalMountainPoints[ridersRaceTotalMountainPoints.length -1 ] = totalRiderPoints;
    //         } 
    //     }
    //     return ridersRaceTotalMountainPoints;    
    // }

    public int getRaceId(){
        return raceId;
    }

    // get name
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
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) {
        Stage stage = new Stage(raceId, stageName, description, length, startTime, type);
        stages.add(stage);
        return stage.getStageID();    
    }

    public static int[] getRaceIds( HashMap<Integer, Race> races) {
        //create an int array to store raceID
        int[] raceIds = new int[races.size()];
        int index = 0;
        
        //iterate through the key(raceID) in hashmap races and add them to array
        for (int raceID : races.keySet()) {
            raceIds[index++] = raceID;
        }
        return raceIds;
    }

    public static int createRace(String name, String description,  HashMap<Integer, Race> races ){
        Race race = new Race(name, description);
        races.put(race.getRaceId(), race);
        return race.getRaceId();
    }

    public static String viewRaceDetails(int raceId, HashMap<Integer, Race> races){
        Race race = races.get(raceId);
        String details = "Race's ID: " + race.getRaceId() + "\n" +
                "Name: " + race.getName() + "\n" +
                "Description: " + race.getDescription() + "\n" +
                "Number of Stage: " + getNumberOfStages(raceId, races) + "\n" +
                "Length: " + race.getLength() + "\n";
        return details;
    }
    
    public static int getNumberOfStages(int raceId, HashMap<Integer, Race> races ){
        Race race = races.get(raceId);
        return race.getStages().size();
    }

    }
   
