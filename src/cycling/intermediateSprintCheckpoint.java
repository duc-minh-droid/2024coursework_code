import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;

public class IntermediateSprintCheckpoint implements Checkpoint,Serializable {
    private int stageID;
    private double location;
    private int checkpointID;
    private  HashMap<Integer,LocalTime> RiderResults = new HashMap<Integer,LocalTime>();

    public IntermediateSprintCheckpoint(int stageID, double location) {
        HelperFunction hf = new HelperFunction();
        this.stageID = stageID;
        this.location = location;
        this.checkpointID = hf.generateUniqueId();

    }
    public int getCheckpointID() {
        return checkpointID;
    }

    public CheckpointType getCheckpointType() {
        return CheckpointType.SPRINT;
    }

    public HashMap<Integer,LocalTime> getRiderResults(){
        return RiderResults;
    }

    public void putRiderResult(int id, LocalTime time){
        RiderResults.put(id, time);
    }
}
