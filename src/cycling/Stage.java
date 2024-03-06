
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.time.Duration;
import java.util.Comparator;

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
    private HashMap<Integer, LocalTime> ridersElapsedTimes = new HashMap<Integer, LocalTime>();
    private int[] riderRank = new int[ridersElapsedTimes.size()];

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

        ridersElapsedTimes.put(riderID, elapsedNano);
        riderResults.put(riderID, updatedCheckpointTimes);
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) {
        LocalTime[] elapesedTimes = ridersElapsedTimes.values().toArray(new LocalTime[0]);
        Arrays.sort(elapesedTimes, Comparator.naturalOrder());
        LocalTime[] sortedElapesedTimes = new LocalTime[elapesedTimes.length];
        System.arraycopy(elapesedTimes, 0, sortedElapesedTimes, 0, elapesedTimes.length);
        LocalTime[] altElapsTimes  = new LocalTime[elapesedTimes.length];
        System.arraycopy(sortedElapesedTimes, 0, altElapsTimes, 0, sortedElapesedTimes.length);
        for (int i = 0; i < sortedElapesedTimes.length; i++) {
            if (i==0) continue;
            if (Duration.between(sortedElapesedTimes[i], sortedElapesedTimes[i-1]).compareTo(Duration.ofSeconds(1)) < 0) {
                System.out.println(sortedElapesedTimes[i-1] + " - " + sortedElapesedTimes[i] + " < 1 second");
                altElapsTimes[i] = altElapsTimes[i-1];
                System.out.println("Which means " + altElapsTimes[i-1] + " - " + altElapsTimes[i]);
            }
        }
        for (LocalTime lt : altElapsTimes) {
            System.out.println(lt);
        }
        return null;
    }

    public HashMap<Integer, LocalTime[]> getRiderResults() {
        return riderResults;
    }

    public int getNumberOfCheckpoints(){
        return checkpoints.size();
    }

    public int[] getRiderRank(){
       LocalTime[] sortedElapesedTimes ;
       return null;
    }
}
