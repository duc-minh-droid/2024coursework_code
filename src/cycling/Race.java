import java.time.LocalDateTime;
import java.util.ArrayList;

public class Race {
    private static int counter = 0;
    private final int raceID;
    private String name;
    private String description;
    private static int numberOfStages = 0;
    private double length;
    private ArrayList<Stage> stages = new ArrayList<Stage>();

    public Race(String name, String description) {
        this.name = name;
        this.description = description;
        this.raceID = counter++;
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


    public int getNumberOfStage(){
        return numberOfStages;
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
        numberOfStages++;
        return stage.getStageID();    
    }
}
