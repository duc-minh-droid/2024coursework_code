public class IntermediateSprintCheckpoint extends Checkpoint {
    private int stageID;
    private double location;
    private int checkpointID;

    public IntermediateSprintCheckpoint(int stageID, double location, int checkpointID) {
        super(location, checkpointID, stageID);
        this.stageID = stageID;
        this.location = location;
        this.checkpointID = checkpointID;

    }
}
