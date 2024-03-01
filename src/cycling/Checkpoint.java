
public class Checkpoint {
    private int stageId;
    private double location;
    private int checkpointId;

    public Checkpoint(double location, int checkpointId, int stageId) {
        this.location = location;
        this.checkpointId = checkpointId;
        this.stageId = stageId;
    }
}
