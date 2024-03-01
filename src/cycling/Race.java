
public class Race {
    private static int counter = 0;
    private final int raceID;
    private String name;
    private String description;
    private int numberOfStages;
    private double length;

    public Race( String name, String description) {
        this.name = name;
        this.description = description;
        this.raceID = counter++;
    }


    public int getRaceID() {
        return raceID;
    }

    //get name
    public String getName(){
        return name;
    }
}




