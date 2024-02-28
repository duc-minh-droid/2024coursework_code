package cycling;

public class Race {
    private int raceID;
    private String name;
    private String description;
    private int numberOfStages;
    private double length;

    public Race(int raceID, String name, String description, int numberOfStages, double length) {
        this.raceID = raceID;
        this.name = name;
        this.description = description;
        this.numberOfStages = numberOfStages;
        this.length = length;
    }
}
