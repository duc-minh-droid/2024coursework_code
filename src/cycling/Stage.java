
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void recordRiderResult(int riderID, LocalTime[] checkpoinTimes){
        riderResults.put(riderID, checkpoinTimes );
    }

    public HashMap<Integer, LocalTime[]> getRiderResults() {
        return riderResults;
    }

    public int getNumberOfCheckpoints(){
        return checkpoints.size();
    }
}
