
public class categorizedClimbCheckpoint extends Checkpoint {
    private double location;
    private CheckpointType checkpointType;
    private double averageGradient;
    private double length;

    public categorizedClimbCheckpoint(int stageId, double location, CheckpointType checkpointType,
            double averageGradient, double length, int checkpointId) {
        super(location, checkpointId, stageId);
        this.location = location;
        this.checkpointType = checkpointType;
        this.averageGradient = averageGradient;
        this.length = length;
    }
}
