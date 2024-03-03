public class IntermediateSprintCheckpoint implements Checkpoint {
    private int stageID;
    private double location;
    private int checkpointID;

    public IntermediateSprintCheckpoint(int stageID, double location, int checkpointID) {
        this.stageID = stageID;
        this.location = location;
        this.checkpointID = checkpointID;

    }
    public int getCheckpointID() {
        return checkpointID;
    }
}
