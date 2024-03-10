import java.time.LocalTime;

class CheckpointTime implements StageTime {
    private LocalTime time;
    private CheckpointType type;

    public CheckpointTime(LocalTime time, CheckpointType type) {
        this.time = time;
        this.type = type;
    }

    public LocalTime getTime() {
        return time;
    }

    public CheckpointType getCheckpointType() {
        return type;
    }
}