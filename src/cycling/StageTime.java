import java.time.LocalTime;

interface StageTime {
    public LocalTime getTime();
    public CheckpointType getCheckpointType();
}