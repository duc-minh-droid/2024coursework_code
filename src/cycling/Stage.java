
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.io.Serializable;
import java.time.Duration;
import java.util.Comparator;
import java.util.LinkedHashMap;

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
    private LinkedHashMap<Integer, Integer> ridersTotalMountainPointsInStage = new LinkedHashMap<Integer, Integer>();
    private LinkedHashMap<Integer, Integer> ridersTotalPointsInStage = new LinkedHashMap<Integer, Integer>();

    public Stage(int raceID, String stageName, String description, double length,
            LocalDateTime startTime, StageType type) {
        HelperFunction hf = new HelperFunction();
        this.stageID = hf.generateUniqueId();
        this.raceID = raceID;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
    }

    public LinkedHashMap<Integer, Integer> getRidersTotalMountainPointsInStage(HashMap<Integer, Race> races) {
        getRidersMountainPoints(races); //call for
        return ridersTotalMountainPointsInStage;
    }
    public LinkedHashMap<Integer, Integer> getRidersTotalPointsInStage(HashMap<Integer, Race> races) {
        getRidersPoints(races);
        return ridersTotalPointsInStage;
    }

    private int calcMountainPositionCheckpoint(LocalTime mountTime, int checkpointID) {
        for (Checkpoint checkpoint : checkpoints) {
            if (checkpoint.getCheckpointID() == checkpointID) {
                LocalTime[] times = checkpoint.getRiderResults().values().toArray(new LocalTime[0]);
                Arrays.sort(times, Comparator.naturalOrder());
                for (int i = 0; i < times.length; i++) {
                    System.out.println(times[i] + " not equals to " + mountTime);
                    if (times[i].equals(mountTime)) {
                        return i;
                    }
                }
            
            }
        }
        return -1;
    }

    private int calcSprintPositionCheckpoint(LocalTime time, int checkpointID) {
        for (Checkpoint checkpoint : checkpoints) {
            if (checkpoint.getCheckpointID() == checkpointID) {
                LocalTime[] times = checkpoint.getRiderResults().values().toArray(new LocalTime[0]);
                Arrays.sort(times, Comparator.naturalOrder());
                for (int i = 0; i < times.length; i++) {
                    if (times[i].equals(time)) {
                        return i;
                    }
                }
            
            }
        }
        return -1;
    }

    private LocalTime[] getRidersTotalElapsedTime() {
        
        LocalTime[] times = {};
        for (LocalTime[] lts : riderResults.values()) {
            times = Arrays.copyOf(times, times.length + 1);
            times[times.length - 1] = lts[lts.length - 1];
        }
        Arrays.sort(times, Comparator.naturalOrder());
        return times;
    }

    private int calcPositionCheckpoint(LocalTime time) {
        LocalTime[] times = getRidersTotalElapsedTime();
        for (int i = 0; i < times.length; i++) {
            if (times[i].equals(time)) {
                return i;
            }
        }
        return -1;
    }

    public int[] getRidersPoints(HashMap<Integer, Race> races){
        int[] ridersRanks = getRidersRankInStage(races);
        int[] points = {};
        HelperFunction hf = new HelperFunction();
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
                riderPoints += hf.retrieveStagePoint(
                    calcPositionCheckpoint(
                        hf.getElapsedTime(
                            riderResults.get(0).getTime(), riderResults.get(riderResults.size()-1).getTime()
                        )
                    ), type);
            
            // Add all intermediate sprint points in stage
            for (int i = 0; i < riderResults.size(); i++) {
                if (i == 0 || i == riderResults.size() - 1) continue;
                StageTime st = riderResults.get(i);
                CheckpointType type = st.getCheckpointType();
                LocalTime time = st.getTime();
                int checkpointID = checkpoints.get(checkpointIndex).getCheckpointID();
                
                if (type == CheckpointType.SPRINT) {
                    riderPoints += hf.retrieveSprintPoint(calcSprintPositionCheckpoint(time, checkpointID));
                    checkpointIndex++;
                }
            }
            points = Arrays.copyOf(points, points.length+1);
            points[points.length - 1] = riderPoints;
            ridersTotalPointsInStage.put(riderID, riderPoints);
        }
        return points;
    }

    public int[] getRidersMountainPoints(HashMap<Integer, Race> races) {
        int[] ridersRanks = getRidersRankInStage(races);
        int[] mountainPoints = {};
        HelperFunction hf = new HelperFunction();
        // Get each rider's points for each rider's rank
        for (int riderID : ridersRanks) {
            System.out.println();
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
                    riderMountainPoints += hf.retrieveMountainPoint(calcMountainPositionCheckpoint(time, checkpointID), type);
                    checkpointIndex++;
                }
            }
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
        Duration elapsedTime = Duration.between(checkpointTimes[0], checkpointTimes[checkpointTimes.length - 1]);

        // Convert elapsed time to nanos
        LocalTime elapsedNano = LocalTime.MIDNIGHT
                .plusHours(elapsedTime.toHours())
                .plusMinutes(elapsedTime.toMinutesPart())
                .plusSeconds(elapsedTime.toSecondsPart())
                .plusNanos(elapsedTime.toNanosPart());

        // Append elapsed time to the array
        LocalTime[] updatedCheckpointTimes = Arrays.copyOf(checkpointTimes, checkpointTimes.length + 1);
        updatedCheckpointTimes[checkpointTimes.length] = elapsedNano;

        ridersAdjustedElapsedTimes.put(riderID, elapsedNano);
        riderResults.put(riderID, updatedCheckpointTimes);
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
        LocalTime[] elapesedTimes = ridersAdjustedElapsedTimes.values().toArray(new LocalTime[0]);
        Arrays.sort(elapesedTimes, Comparator.naturalOrder());
        LocalTime[] sortedElapesedTimes = new LocalTime[elapesedTimes.length];
        System.arraycopy(elapesedTimes, 0, sortedElapesedTimes, 0, elapesedTimes.length);
        LocalTime[] altElapsTimes  = new LocalTime[elapesedTimes.length];
        System.arraycopy(sortedElapesedTimes, 0, altElapsTimes, 0, sortedElapesedTimes.length);
        for (int i = 0; i < sortedElapesedTimes.length; i++) {
            if (i==0) continue;
            Duration elapsedTime = Duration.ofHours(24).minus(Duration.between(sortedElapesedTimes[i], sortedElapesedTimes[i-1]));
            LocalTime elapsedNano = LocalTime.MIN
            .plusHours(elapsedTime.toHours())
            .plusMinutes(elapsedTime.toMinutesPart())
            .plusSeconds(elapsedTime.toSecondsPart())
            .plusNanos(elapsedTime.toNanosPart());
            final long ONE_SECOND = 1_000_000_000L;
            if (elapsedNano.toNanoOfDay()<ONE_SECOND) {
                altElapsTimes[i] = altElapsTimes[i-1];
            }
        }
        int[] ridersIDs = {};
        for (int riderID : ridersAdjustedElapsedTimes.keySet()) {
            for (LocalTime lt : sortedElapesedTimes) {
                if (ridersAdjustedElapsedTimes.get(riderID).equals(lt)) {
                    ridersIDs = Arrays.copyOf(ridersIDs, ridersIDs.length + 1);
                    ridersIDs[ridersIDs.length - 1] = riderID;
                }
            }
        }
        for (int i = 0; i < altElapsTimes.length; i++) {
            ridersAdjustedElapsedTimes.put(ridersIDs[i], altElapsTimes[i]);
            // System.out.println(altElapsTimes[i]);
        }
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int riderId) {
        // convertElapsedTimesToAdjustedElapsedTimes();
        for ( int ID : ridersAdjustedElapsedTimes.keySet()){
            if (ID == riderId){
                return ridersAdjustedElapsedTimes.get(ID);
            }
        }
        return null;
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
        // convertElapsedTimesToAdjustedElapsedTimes();
        return ridersAdjustedElapsedTimes;
    }

    public int[] getRidersRankInStage(HashMap<Integer, Race> races) {
        int[] result = {};
        for (int riderID : ridersAdjustedElapsedTimes.keySet()) {
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = riderID;
        }
        return result;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId, HashMap<Integer, Race> races)  {
        HelperFunction hf = new HelperFunction();
            LocalTime[] adjRank = {};
            for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()){
                if (stage.getStageID() == stageId){
                    for (LocalTime time : stage.getRidersAdjustedElapsedTimes().values()){
                        adjRank = Arrays.copyOf(adjRank, adjRank.length + 1);
                        adjRank[adjRank.length -1 ] = time;
                    }
                    break;
                }
            } 
        return adjRank;
    }

}
