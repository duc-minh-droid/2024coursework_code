
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.io.Serializable;
import java.time.Duration;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class Stage implements Serializable {
    private int stageID;
    private int raceID;
    private String stageName;
    private String description;
    private double length;
    private LocalDateTime startTime;
    private StageType type;
    private ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
    private String stageState = "";
    private HashMap<Integer, LocalTime[]> riderResults = new HashMap<Integer, LocalTime[]>();
    private HashMap<Integer, ArrayList<StageTime>> riderObjectResults = new HashMap<Integer, ArrayList<StageTime>>();
    private LinkedHashMap<Integer, LocalTime> ridersAdjustedElapsedTimes = new LinkedHashMap<Integer, LocalTime>();
    private LinkedHashMap<Integer, LocalTime> ridersElapsedTimes = new LinkedHashMap<Integer, LocalTime>();
    private LinkedHashMap<Integer, Integer> ridersTotalMountainPointsInStage = new LinkedHashMap<Integer, Integer>();
    private LinkedHashMap<Integer, Integer> ridersTotalPointsInStage = new LinkedHashMap<Integer, Integer>();

    public Stage(int raceID, String stageName, String description, double length,
            LocalDateTime startTime, StageType type) {
        
        this.stageID = HelperFunction.generateUniqueId();
        this.raceID = raceID;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
    }

    public LinkedHashMap<Integer, Integer> getRidersTotalMountainPointsInStage() {
        getRidersMountainPoints(); //call for
        return ridersTotalMountainPointsInStage;
    }
    public LinkedHashMap<Integer, Integer> getRidersTotalPointsInStage() {
        getRidersPoints();
        return ridersTotalPointsInStage;
    }

     /**
     * Calculates the position of a rider based on their arrival time at a mountain checkpoint.
     *
     * @param mountTime     The arrival time of the rider at the mountain checkpoint.
     * @param checkpointID  The ID of the mountain checkpoint.
     * @return              The position of the rider based on their arrival time. Returns -1 if the checkpoint is not found.
     */
    private int calcMountainPositionCheckpoint(LocalTime mountTime, int checkpointID) {
        // Iterate through checkpoints to find the one with the given checkpointID
        for (Checkpoint checkpoint : checkpoints) {
            if (checkpoint.getCheckpointID() == checkpointID) {
                // Get the arrival times of all riders at this checkpoint and sort them in ascending order
                LocalTime[] times = checkpoint.getRiderResults().values().toArray(new LocalTime[0]);
                Arrays.sort(times, Comparator.naturalOrder());

                // Find the position of the rider based on their arrival time
                for (int i = 0; i < times.length; i++) {
                    if (times[i].equals(mountTime)) {
                        return i; // Return the position if the arrival time matches
                    }
                }
            }
        }
        return -1; // Return -1 if the checkpoint is not found
    }

    /**
     * Calculates the position of a rider based on their arrival time at a sprint checkpoint.
     *
     * @param time          The arrival time of the rider at the sprint checkpoint.
     * @param checkpointID  The ID of the sprint checkpoint.
     * @return              The position of the rider based on their arrival time. Returns -1 if the checkpoint is not found.
     */
    private int calcSprintPositionCheckpoint(LocalTime time, int checkpointID) {
        // Iterate through checkpoints to find the one with the given checkpointID
        for (Checkpoint checkpoint : checkpoints) {
            if (checkpoint.getCheckpointID() == checkpointID) {
                // Get the arrival times of all riders at this checkpoint and sort them in ascending order
                LocalTime[] times = checkpoint.getRiderResults().values().toArray(new LocalTime[0]);
                Arrays.sort(times, Comparator.naturalOrder());

                // Find the position of the rider based on their arrival time
                for (int i = 0; i < times.length; i++) {
                    if (times[i].equals(time)) {
                        return i; // Return the position if the arrival time matches
                    }
                }
            }
        }
        return -1; // Return -1 if the checkpoint is not found
    }

    /**
     * Retrieves the finish times of all riders and returns them in sorted order.
     *
     * @return An array of LocalTime representing the finish times of all riders, sorted in ascending order.
     */
    private LocalTime[] getRidersFinishTime() {
        // Initialize an empty array to store finish times
        LocalTime[] times = {};

        // Iterate over the values (arrays of LocalTime) in the riderResults map
        for (LocalTime[] lts : riderResults.values()) {
            // Increase the size of the times array by 1
            times = Arrays.copyOf(times, times.length + 1);
            // Store the finish time of the rider (second to last element in the array) in the times array
            times[times.length - 1] = lts[lts.length - 2];
        }

        // Sort the finish times array in ascending order
        Arrays.sort(times, Comparator.naturalOrder());

        return times; // Return the sorted array of finish times
    }

    /**
     * Calculates the position of a rider based on their finish time.
     *
     * @param time  The finish time of the rider.
     * @return      The position of the rider based on their finish time. Returns -1 if the rider's finish time is not found.
     */
    private int calcPositionCheckpoint(LocalTime time) {
        // Get the finish times of all riders and sort them
        LocalTime[] times = getRidersFinishTime();

        // Iterate over the sorted finish times to find the position of the rider with the given time
        for (int i = 0; i < times.length; i++) {
            if (times[i].equals(time)) {
                return i; // Return the position if the finish time matches
            }
        }

        return -1; // Return -1 if the rider's finish time is not found
    }

    public int[] getRidersPoints(){
        int[] ridersRanks = getRidersAdjustedRankInStage();
        int[] points = {};
        
        // Get each rider's points for each rider's rank
        for (int riderID : ridersRanks) {
            ArrayList<StageTime> riderResults = riderObjectResults.get(riderID);
            int riderPoints = 0;
            int checkpointIndex = 0;
            // Return empty array if a rider's result is not registered
            if (!riderObjectResults.containsKey(riderID) || riderResults.size()-2 != checkpoints.size()  ) {
                int[] emptyArray = {};
                return emptyArray;
            }
            // Add stage point
                riderPoints += HelperFunction.retrieveStagePoint(
                    calcPositionCheckpoint(riderResults.get(riderResults.size()-1).getTime()), type);
            
            // Add all intermediate sprint points in stage
            for (int i = 0; i < riderResults.size(); i++) {
                if (i == 0 || i == riderResults.size() - 1) continue;
                StageTime st = riderResults.get(i);
                CheckpointType type = st.getCheckpointType();
                LocalTime time = st.getTime();
                int checkpointID = checkpoints.get(checkpointIndex).getCheckpointID();
                
                if (type == CheckpointType.SPRINT) {
                    riderPoints += HelperFunction.retrieveSprintPoint(calcSprintPositionCheckpoint(time, checkpointID));
                    checkpointIndex++;
                }
            }
            // Add element to the returning array
            points = Arrays.copyOf(points, points.length+1);
            points[points.length - 1] = riderPoints;
            ridersTotalPointsInStage.put(riderID, riderPoints);
        }
        return points;
    }

    public int[] getRidersMountainPoints() {
        int[] ridersRanks = getRidersAdjustedRankInStage();
        int[] mountainPoints = {};
        
        // Get each rider's points for each rider's rank
        for (int riderID : ridersRanks) {
            if (!riderObjectResults.containsKey(riderID)  ) {
                int[] emptyArray = {};
                return emptyArray;
            }
            ArrayList<StageTime> riderResults = riderObjectResults.get(riderID);
            int riderMountainPoints = 0;
            int checkpointIndex = 0;
            if (riderResults.size()-2 != checkpoints.size()) {
                int[] emptyArray = {};
                return emptyArray;
            }
            // Summing the points for each climb checkpoint
            for (int i = 0; i < riderResults.size(); i++) {
                if (i == 0 || i == riderResults.size() - 1) continue;
                StageTime st = riderResults.get(i);
                CheckpointType type = st.getCheckpointType();
                LocalTime time = st.getTime();
                
                int checkpointID = checkpoints.get(checkpointIndex).getCheckpointID();
                if (type != CheckpointType.SPRINT) {
                    riderMountainPoints += HelperFunction.retrieveMountainPoint(calcMountainPositionCheckpoint(time, checkpointID), type);
                    checkpointIndex++;
                }
            }
            // Add elements to the returning array
            mountainPoints = Arrays.copyOf(mountainPoints, mountainPoints.length+1);
            mountainPoints[mountainPoints.length - 1] = riderMountainPoints;
            ridersTotalMountainPointsInStage.put(riderID, riderMountainPoints);
        }
        return mountainPoints;
    }
    
    public int getStageID(){
        return stageID;
    }
        
    public String getStageName(){
        return stageName;
    }

    public String getDescription(){
        return description;
    }

    public double getLength(){
        return length;
    }

    public StageType getType(){
        return type;
    }

    public ArrayList<Checkpoint> getCheckpoints(){
        return checkpoints;
    }

    public void addCheckpoint(Checkpoint cp) {
        checkpoints.add(cp);
    }

    public void concludeStagePreparation() {
        stageState = "waiting for results";
    }

    public String getStageState() {
        return stageState;
    }

    public void recordRiderResult(int riderID, LocalTime[] checkpointTimes){
        Duration elapsedDuration = Duration.between(checkpointTimes[0], checkpointTimes[checkpointTimes.length - 1]);

        // Convert elapsed duration to localtime
        LocalTime elapsedTime = LocalTime.MIDNIGHT
                .plusHours(elapsedDuration.toHours())
                .plusMinutes(elapsedDuration.toMinutesPart())
                .plusSeconds(elapsedDuration.toSecondsPart())
                .plusNanos(elapsedDuration.toNanosPart());

        // Append elapsed time to the array
        LocalTime[] updatedCheckpointTimes = Arrays.copyOf(checkpointTimes, checkpointTimes.length + 1);
        updatedCheckpointTimes[checkpointTimes.length-1] = elapsedTime;
        ridersAdjustedElapsedTimes.put(riderID, elapsedTime);
        riderResults.put(riderID, updatedCheckpointTimes);

        // Add checkpoint times for calculating points
        ArrayList<StageTime> objectResult = new ArrayList<StageTime>();
        for (int i = 0; i < checkpointTimes.length; i++) {  
            if (i == 0 || i == checkpointTimes.length - 1) {
                BeginOrStartTime time = new BeginOrStartTime(checkpointTimes[i]);
                objectResult.add(time);
            } else {
                CheckpointTime time = new CheckpointTime(checkpointTimes[i], checkpoints.get(i-1).getCheckpointType());
                objectResult.add(time);
                checkpoints.get(i-1).putRiderResult(riderID, checkpointTimes[i]);
            }
        }
        riderObjectResults.put(riderID, objectResult);
        convertElapsedTimesToAdjustedElapsedTimes();
    }

    public void convertElapsedTimesToAdjustedElapsedTimes() {
        LocalTime[] elapsedTimes = ridersAdjustedElapsedTimes.values().toArray(new LocalTime[0]);
        Arrays.sort(elapsedTimes, Comparator.naturalOrder());
        LocalTime[] sortedElapsedTimes = new LocalTime[elapsedTimes.length];
        System.arraycopy(elapsedTimes, 0, sortedElapsedTimes, 0, elapsedTimes.length);
        LocalTime[] altElapsedTimes  = new LocalTime[elapsedTimes.length];
        System.arraycopy(sortedElapsedTimes, 0, altElapsedTimes, 0, sortedElapsedTimes.length);
        for (int i = 0; i < sortedElapsedTimes.length; i++) {
            if (i==0) continue;
            Duration elapsedDuration = Duration.ofHours(24).minus(Duration.between(sortedElapsedTimes[i], sortedElapsedTimes[i-1]));
            LocalTime elapsedTime = LocalTime.MIN
            .plusHours(elapsedDuration.toHours())
            .plusMinutes(elapsedDuration.toMinutesPart())
            .plusSeconds(elapsedDuration.toSecondsPart())
            .plusNanos(elapsedDuration.toNanosPart());
            final long ONE_SECOND = 1_000_000_000L;
            if (elapsedTime.toNanoOfDay()<ONE_SECOND) {
                altElapsedTimes[i] = altElapsedTimes[i-1];
            }
        }
        int[] ridersIDs = {};
        for (int riderID : ridersAdjustedElapsedTimes.keySet()) {
            for (LocalTime lt : sortedElapsedTimes) {
                if (ridersAdjustedElapsedTimes.get(riderID).equals(lt)) {
                    ridersIDs = Arrays.copyOf(ridersIDs, ridersIDs.length + 1);
                    ridersIDs[ridersIDs.length - 1] = riderID;
                }
            }
        }
        for (int i = 0; i < altElapsedTimes.length; i++) {
            ridersAdjustedElapsedTimes.put(ridersIDs[i], altElapsedTimes[i]);
        }
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int riderId) {
        return ridersAdjustedElapsedTimes.get(riderId);
    }

    public HashMap<Integer, LocalTime[]> getRiderResults() {
        return riderResults;
    }

    public HashMap<Integer, ArrayList<StageTime>> getRiderObjectResults() {
        return riderObjectResults;
    }

    public int getNumberOfCheckpoints(){
        return checkpoints.size();
    }

    public LinkedHashMap<Integer, LocalTime> getRidersAdjustedElapsedTimes() {
        return ridersAdjustedElapsedTimes;
    }

    public int[] getRidersAdjustedRankInStage() {
        int[] result = {};
        // Loop through each value in ridersAdjustedElapsedTimes and add them to returning array
        for (int riderID : ridersAdjustedElapsedTimes.keySet()) {
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = riderID;
        }
        return result;
    }

    public int[] getRidersRankInStage() {
        int[] result = {};
        LocalTime[] lt = {};
        for (int riderID : riderResults.keySet()) {
            LocalTime time = riderResults.get(riderID)[riderResults.get(riderID).length-1];
            ridersElapsedTimes.put(riderID, time);
            // System.out.println(time);
        }
        ridersElapsedTimes = HelperFunction.sortLinkedHashMapByValue(ridersElapsedTimes);
        for (int riderID : ridersElapsedTimes.keySet()) {
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = riderID;
        }
        return result;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId, HashMap<Integer, Race> races)  {
        LocalTime[] adjRank = {};
        // Loop through each value in ridersAdjustedElapsedTimes and add them to returning array
        for (LocalTime time : ridersAdjustedElapsedTimes.values()){
            adjRank = Arrays.copyOf(adjRank, adjRank.length + 1);
            adjRank[adjRank.length -1 ] = time;
        }
        return adjRank;
    }

    public int addCategorizedClimbToStage(Double location, CheckpointType type, Double averageGradient,
    Double length){
        
        //create a new Climb checkpoint
        CategorizedClimbCheckpoint cp = new CategorizedClimbCheckpoint(stageID, location, type, averageGradient, length);
        
        //add the recent created checkpoint to the stage
        addCheckpoint(cp);

        // return the recent created checkpoint's Id
        return cp.getCheckpointID();
        
    }

    public int addIntermediateSprintToStage(double location){
        
        //create a new Sprint checkpoint
        IntermediateSprintCheckpoint cp = new IntermediateSprintCheckpoint(stageID, location);
        
        //add the recent created checkpoint to the stage
        addCheckpoint(cp);

        // return the recent created checkpoint's Id
        return cp.getCheckpointID();
    }

    public LocalTime[] getRiderResultsInStage(int riderId) {
        
        LocalTime[] result = riderResults.get(riderId);
        LocalTime[] lt = {};
        for (int i = 0; i < result.length; i++) {
            if (i == 0 || i == result.length-1) {
                continue;
            } else {
                lt = Arrays.copyOf(lt, lt.length+1);
                lt[lt.length-1] = result[i];
            }
        }
        return lt;
    }

    public int[] getStageCheckpoints(){
        // create an arraylist of checkpoints
        ArrayList<Checkpoint> checkpoints = getCheckpoints();

        // create an array to store checkpoints' Id
        int[] stageCheckpointsId = new int[checkpoints.size()];

        // Iterate through the checkpoints arraylist and add checkppointId to the array above
        for (int i = 0; i < checkpoints.size(); i++) {
            stageCheckpointsId[i] = checkpoints.get(i).getCheckpointID();
        }

        // return the array with checkpoint Ids in stage
        return stageCheckpointsId;
    }



   
}
