public class IntermediateSprintCheckpoint implements Checkpoint {
    private int stageID;
    private double location;
    private int checkpointID;

    public IntermediateSprintCheckpoint(int stageID, double location) {
        HelperFunction hf = new HelperFunction();
        this.stageID = stageID;
        this.location = location;
        this.checkpointID = hf.generateUniqueId();

    }
    public int getCheckpointID() {
        return checkpointID;
    }
}
