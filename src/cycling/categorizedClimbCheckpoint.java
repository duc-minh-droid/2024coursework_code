public class CategorizedClimbCheckpoint implements Checkpoint {
    private int stageID;
    private double location;
    private CheckpointType checkpointType;
    private double averageGradient;
    private double length;
    private int checkpointID;

    public CategorizedClimbCheckpoint(int stageId, double location, CheckpointType checkpointType,
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
}
