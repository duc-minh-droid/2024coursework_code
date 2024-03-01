
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;


public class CyclingPortalImpl implements CyclingPortal {
    private static HashMap<Integer, Race> races = new HashMap<Integer, Race>();

    public static void main(String[] args) throws InvalidNameException, IllegalNameException, IDNotRecognisedException, InvalidLengthException{
        CyclingPortal cp = new CyclingPortalImpl();
        int LeMansID = cp.createRace("LeMans", "GO GO GO GO");
        int[] ids = cp.getRaceIds();
        for (int id:ids) {
            // System.out.print(id);
        }
        int LeBonkID = cp.addStageToRace(LeMansID, "COCO", "desc", 6.0, null, null);
        int LeDussyID = cp.addStageToRace(LeMansID, "ConCo", "desc", 6.0, null, null);
        int[] stageIDs = cp.getRaceStages(LeMansID);
        cp.removeStageById(LeBonkID);
        System.out.println(cp.getNumberOfStages(LeMansID));

    }

    public int[] getRaceIds() {
        int[] raceIds = new int[races.size()];
        int index = 0;
        for (Race race : races.values()) {
            raceIds[index++] = race.getRaceID();
        }
        return raceIds;
    }

    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {

        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Race's name can not be empty");
        }
        if (name.length() > 30) {
            throw new InvalidNameException("Race's name can not exceed 30 character");
        }
        if (name.contains(" ")) {
            throw new InvalidNameException("Race's name can not contain white space");
        }

        HelperFunction hf = new HelperFunction();
        if (hf.getRacesNames(races).contains(name)) {
            throw new IllegalNameException(("Race's name already existing"));
        }
        Race race = new Race(name, description);
        races.put(race.getRaceID(), race);
        return race.getRaceID();

    }

    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        String details = "Race's ID: "       + race.getRaceID()        +"\n" +
                         "Name: "            + race.getName()          +"\n" +
                         "Description: "     + race.getDescription()   +"\n" + 
                         "Number of Stage: " + race.getNumberOfStage() +"\n" + 
                         "Length: "          + race.getLength()        +"\n";
        return details;
    }

    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");}
        races.remove(raceId);
    }

    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");}
        return race.getNumberOfStage();
    }

    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");}
        if (stageName== null || stageName.isEmpty()) {
            throw new InvalidNameException("Stage's name can not be empty");
        }
        if (stageName.length() > 30) {
            throw new InvalidNameException("Stage's name can not exceed 30 character");
        }
        if (stageName.contains(" ")) {
            throw new InvalidNameException("Stage's name can not contain white space");
        }
        if (length < 5.0){
            throw new InvalidLengthException ("The stage's length must be longer than 5km");
        }
        HelperFunction hf = new HelperFunction();
        if (hf.getStagesNames(races).contains(stageName)){
            throw new IllegalNameException("This stage name is already existing");
        }

        
        return race.addStageToRace(raceId, stageName, description, length, startTime, type);

    }

    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");}
        int[] raceStageIDs = new int[race.getNumberOfStage()];
        int index = 0;
        for (Stage stage : race.getStages()) {
            raceStageIDs[index] = stage.getStageID();
            index++;
        }
        return raceStageIDs;
    }

    public double getStageLength(int stageId) throws IDNotRecognisedException {
        
        // if (stageId)
        HelperFunction hf = new HelperFunction();
        Stage stage = hf.getStageByID(stageId, races);
        return stage.getLength();
    }

    public void removeStageById(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        Stage stage = hf.getStageByID(stageId, races);
        
    }

    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {
        return 0;
    }

    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        // TODO Auto-generated method stub
        return 0;
    }

    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
        // TODO Auto-generated method stub

    }

    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        // TODO Auto-generated method stub

    }

    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        // TODO Auto-generated method stub
        return 0;
    }

    public void removeTeam(int teamId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    public int[] getTeams() {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int createRider(int teamID, String name, int yearOfBirth)
            throws IDNotRecognisedException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return 0;
    }

    public void removeRider(int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
            InvalidStageStateException {
        // TODO Auto-generated method stub

    }

    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public void eraseCyclingPortal() {
        // TODO Auto-generated method stub

    }

    public void saveCyclingPortal(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }

    public void removeRaceByName(String name) throws NameNotRecognisedException {
        // TODO Auto-generated method stub

    }

    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

}
