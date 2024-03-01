
import java.time.LocalDateTime;

public class Stage {
    private int stageID;
    private String stageName;
    private String description;
    private double length;
    private LocalDateTime startTime;
    private StageType type;

    public Stage(int stageID, String stageName, String description, double length,
            LocalDateTime startTime, StageType type) {
        this.stageID = stageID;
        this.stageName = stageName;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
    }
    
}
