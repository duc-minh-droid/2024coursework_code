import java.time.LocalTime;
import java.util.HashMap;
/**
 * Checkpoint
 */
 interface Checkpoint {
    public int getCheckpointID();
    public CheckpointType getCheckpointType();
    public HashMap<Integer,LocalTime> getRiderResults();
    public void putRiderResult(int id, LocalTime time);
} 
