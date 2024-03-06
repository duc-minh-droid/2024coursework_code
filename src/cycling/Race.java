import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Race implements Serializable {
    private final int raceID;
    private String name;
    private String description;
    private double length;
    private ArrayList<Stage> stages = new ArrayList<Stage>();

    public Race(String name, String description) {
        HelperFunction hf = new HelperFunction();
        this.name = name;
        this.description = description;
        this.raceID = hf.generateUniqueId();
    }

    public int getRaceID() {
        return raceID;
    }

    // get name
    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public double getLength(){
        return length;
    }
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) {
        Stage stage = new Stage(raceId, stageName, description, length, startTime, type);
        stages.add(stage);
        return stage.getStageID();    
    }
}
