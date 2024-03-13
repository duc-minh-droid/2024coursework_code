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

}
   
