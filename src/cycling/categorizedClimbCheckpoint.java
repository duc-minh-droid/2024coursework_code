import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;

public class CategorizedClimbCheckpoint implements Checkpoint, Serializable {
    private int stageID;
    private double location;
    private CheckpointType checkpointType;
    private double averageGradient;
    private double length;
    private int checkpointID;
    private HashMap<Integer,LocalTime> RiderResults = new HashMap<Integer,LocalTime>();

    public CategorizedClimbCheckpoint (int stageId, double location, CheckpointType checkpointType,
            double averageGradient, double length) {
        super();
        HelperFunction hf = new HelperFunction();
        this.checkpointID = hf.generateUniqueId();
        this.stageID = stageId;
        this.location = location;
        this.checkpointType = checkpointType;
        this.averageGradient = averageGradient;
        this.length = length;
    }

    public int getCheckpointID() {
        return checkpointID;
    }

    public CheckpointType getCheckpointType() {
        return checkpointType;
    }

    public HashMap<Integer,LocalTime> getRiderResults(){
        return RiderResults;
    }

    public void putRiderResult(int id, LocalTime time){
        RiderResults.put(id, time);
    }
}
