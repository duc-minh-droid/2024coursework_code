
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.time.Duration;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Stage {
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
    private LinkedHashMap<Integer, LocalTime> ridersAdjustedElapsedTimes = new LinkedHashMap<Integer, LocalTime>();

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
    }

    public LocalTime convertElapsedTimesToAdjustedElapsedTimes() {
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
        return null;
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int riderId) {
        convertElapsedTimesToAdjustedElapsedTimes();
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

    public int getNumberOfCheckpoints(){
        return checkpoints.size();
    }

    public LinkedHashMap<Integer, LocalTime> getRidersAdjustedElapsedTimes() {
        convertElapsedTimesToAdjustedElapsedTimes();
        return ridersAdjustedElapsedTimes;
    }

    public int[] getRidersRankInStage(int stageId, HashMap<Integer, Race> races) {
        HelperFunction hf = new HelperFunction();
        int[] result = {};
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                for (int riderID : stage.getRidersAdjustedElapsedTimes().keySet()) {
                    result = Arrays.copyOf(result, result.length + 1);
                    result[result.length - 1] = riderID;
                }
                break;
            }
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
