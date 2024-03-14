import java.io.Serializable;
import java.time.LocalTime;

class BeginOrStartTime implements StageTime, Serializable{
    private LocalTime time;

    public BeginOrStartTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public CheckpointType getCheckpointType() {
        return null;
    };
    
}