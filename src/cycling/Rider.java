
public class Rider {
    private int riderID;
    private int teamID;
    private String riderName;
    private int YearOfBirth;
    

    public Rider(int riderID, String riderName, int YearOfBirth) {
        HelperFunction hf = new HelperFunction();
        this.riderID = hf.generateUniqueId();
        this.riderName = riderName;
        this.YearOfBirth = YearOfBirth;
    }
}
