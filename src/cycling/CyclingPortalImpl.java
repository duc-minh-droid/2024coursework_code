
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CyclingPortalImpl implements CyclingPortal {
    private static HashMap<Integer, Race> races = new HashMap<Integer, Race>();
    private static HashMap<Integer, Team> teams = new HashMap<Integer, Team>();

    public static void main(String[] args) throws InvalidNameException, IllegalNameException, IDNotRecognisedException,
            InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException, DuplicatedResultException, InvalidCheckpointTimesException {
        CyclingPortal cp = new CyclingPortalImpl();
        int LeMansID = cp.createRace("LeMans", "GO GO GO GO");
        int[] ids = cp.getRaceIds();
        for (int id : ids) {
            // System.out.print(id);
        }
        int LeBonkID = cp.addStageToRace(LeMansID, "COCO", "desc", 6.0, null,  StageType.FLAT);
        int LeDussyID = cp.addStageToRace(LeMansID, "ConCo", "desc", 6.0, null, StageType.FLAT);
        int[] stageIDs = cp.getRaceStages(LeMansID);
        // cp.removeStageById(LeBonkID);
        // System.out.println(cp.getNumberOfStages(LeMansID));
        // for (int stageID: cp.getRaceStages(LeMansID)) {
        // System.out.println(stageID);
        // }
        int LeMonkcpID = cp.addCategorizedClimbToStage(LeDussyID, 6.0, CheckpointType.C1, 6.0, 6.0);
        int LeStonkcpID = cp.addIntermediateSprintToStage(LeDussyID, 6.0);
        // for (Stage stage : races.get(LeMansID).getStages()) {
        // if (stage.getStageID() == LeDussyID) {
        // for (Checkpoint chepoi : stage.getCheckpoints()) {
        // System.out.println(chepoi.getCheckpointID());
        // }

        // }
        // }
        cp.removeCheckpoint(LeMonkcpID);
        int[] cpIDs = cp.getStageCheckpoints(LeDussyID);
        for (int id : cpIDs) {
            // System.out.println(id);
        }
        int Lakers = cp.createTeam("laker", "desc");
        int Lakersss = cp.createTeam("rekal", "desc");
        int BronnyID = cp.createRider(Lakers, "Bronny", 2000);
        int B = cp.createRider(Lakers, "Lebro", 1999);
        int C = cp.createRider(Lakers, "Lebr", 1999);

        // cp.removeRider(B);
        // cp.removeTeam(Lakers);
        // // cp.getTeamRiders(Lakers);
        // int[] lakersRiders = cp.getTeamRiders(Lakers);
        // System.out.println("Riders of Lakers:");
        // for (int riderId : lakersRiders) {
        //     System.out.println(riderId);
        // }

        LocalTime[] now = {LocalTime.now(),LocalTime.now(),LocalTime.now() };
        cp.concludeStagePreparation(LeBonkID);
        cp.registerRiderResultsInStage(LeBonkID,BronnyID, now );
        for(Stage stage : races.get(LeMansID).getStages()){
            if ( stage.getStageID() == LeBonkID ){
                for (LocalTime lt : stage.getRiderResults().get(BronnyID) ){
                System.out.println(lt);
                }
            }
        }
    
    }

    public int[] getRaceIds() {
        int[] raceIds = new int[races.size()];
        int index = 0;
        for (int raceID : races.keySet()) {
            raceIds[index++] = raceID;
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
        String details = "Race's ID: " + race.getRaceID() + "\n" +
                "Name: " + race.getName() + "\n" +
                "Description: " + race.getDescription() + "\n" +
                "Number of Stage: " + getNumberOfStages(raceId) + "\n" +
                "Length: " + race.getLength() + "\n";
        return details;
    }

    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        races.remove(raceId);
    }

    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getStages().size();
    }

    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        if (stageName == null || stageName.isEmpty()) {
            throw new InvalidNameException("Stage's name can not be empty");
        }
        if (stageName.length() > 30) {
            throw new InvalidNameException("Stage's name can not exceed 30 character");
        }
        if (stageName.contains(" ")) {
            throw new InvalidNameException("Stage's name can not contain white space");
        }
        if (length < 5.0) {
            throw new InvalidLengthException("The stage's length must be longer than 5km");
        }
        HelperFunction hf = new HelperFunction();
        if (hf.getStagesNames(races).contains(stageName)) {
            throw new IllegalNameException("This stage name is already existing");
        }

        return race.addStageToRace(raceId, stageName, description, length, startTime, type);

    }

    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        int[] raceStageIDs = new int[getNumberOfStages(raceId)];
        int index = 0;
        for (Stage stage : race.getStages()) {
            raceStageIDs[index] = stage.getStageID();
            index++;
        }
        return raceStageIDs;
    }

    public double getStageLength(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }
        Stage stage = hf.getStageByID(stageId, races);
        return stage.getLength();
    }

    public void removeStageById(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }
        int raceID = hf.getRaceIDByStageID(stageId, races);
        ArrayList<Stage> stages = races.get(raceID).getStages();
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getStageID() == stageId) {
                stages.remove(i);
                break;
            }
        }
    }

    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }
        boolean locationIsInvalid = false; 
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (location < 0 || location > stage.getLength()) {
                    locationIsInvalid = true;
                }
            }
        }
        if (locationIsInvalid) {
            throw new InvalidLocationException("Invalid location");
        }

        boolean stageIsInvalid = false;
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() == "waiting for results") {
                    stageIsInvalid = true;
                }
            
            }
        }
        if (stageIsInvalid) {
            throw new InvalidStageStateException("Invalid stage state");
        }
        ArrayList<StageType> StageTypes = new ArrayList<StageType>();
        StageTypes.add(StageType.FLAT);
        StageTypes.add(StageType.MEDIUM_MOUNTAIN);
        StageTypes.add(StageType.HIGH_MOUNTAIN);
        StageTypes.add(StageType.TT);
        if (!StageTypes.contains(hf.getStageByID(stageId,races).getType())) {
            throw new InvalidStageTypeException("Invalid stage type");
        }

        CategorizedClimbCheckpoint cp = new CategorizedClimbCheckpoint(stageId, location, type, averageGradient,
                length);
        ArrayList<Stage> stages = races.get(hf.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                stage.addCheckpoint(cp);
                return cp.getCheckpointID();
            }
        }
        return 0;
    }

    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        boolean locationIsInvalid = false;
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (location < 0 || location > stage.getLength()) {
                    locationIsInvalid = true;
                }
            }
        }
        if (locationIsInvalid) {
            throw new InvalidLocationException("Invalid location");
        }
        boolean stageIsInvalid = false;
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() == "waiting for results") {
                    stageIsInvalid = true;
                }
            
            }
        }
        if (stageIsInvalid) {
            throw new InvalidStageStateException("Invalid stage state");
        }
        // NO type param to throw exception
        if (false) {
            throw new InvalidStageTypeException("Invalid stage type");
        }

        IntermediateSprintCheckpoint cp = new IntermediateSprintCheckpoint(stageId, location);
        ArrayList<Stage> stages = races.get(hf.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                stage.addCheckpoint(cp);
                return cp.getCheckpointID();
            }
        }
        return 0;
    }

    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
        HelperFunction hf = new HelperFunction();

        if (!hf.getAllCheckpoints(races).contains(checkpointId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByCheckpointID(checkpointId,races)).getStages()) {
            if (stage.getStageID() == hf.getStageIDByCheckpointID(checkpointId, races)) {
                if (stage.getStageState() == "waiting for results") {
                    throw new InvalidStageStateException("Invalid stage state");
                }
            }
        }
        int stageID = hf.getStageIDByCheckpointID(checkpointId, races);
        ArrayList<Stage> stages = races.get(hf.getRaceIDByCheckpointID(checkpointId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageID) {
                ArrayList<Checkpoint> checkpoints = stage.getCheckpoints();
                for (int i = 0; i < checkpoints.size(); i++) {
                    if (checkpoints.get(i).getCheckpointID() == checkpointId) {
                        checkpoints.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
         boolean stageIsInvalid = false;
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() == "waiting for results") {
                    stageIsInvalid = true;
                }
            
            }
        }
        if (stageIsInvalid) {
            throw new InvalidStageStateException("Invalid stage state");
        }
        ArrayList<Stage> stages = races.get(hf.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                stage.concludeStagePreparation();
            }
        }
    }

    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        ArrayList<Stage> stages = races.get(hf.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                ArrayList<Checkpoint> checkpoints = stage.getCheckpoints();
                int[] stageCheckpoints = new int[checkpoints.size()];
                for (int i = 0; i < checkpoints.size(); i++) {
                    stageCheckpoints[i] = checkpoints.get(i).getCheckpointID();
                }
                return stageCheckpoints;
            }
        }
        return null;
    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Team's name can not be empty");
        }
        if (name.length() > 30) {
            throw new InvalidNameException("Team's name can not exceed 30 character");
        }
        if (name.contains(" ")) {
            throw new InvalidNameException("Team's name can not contain white space");
        }

        HelperFunction hf = new HelperFunction();
        if (hf.getTeamsNames(teams).contains(name)) {
            throw new IllegalNameException(("Team's name has already existed"));
        }
        Team team = new Team(name, description);
        teams.put(team.getTeamID(), team);
        return team.getTeamID();

    }

    public void removeTeam(int teamId) throws IDNotRecognisedException {
        Team team = teams.get(teamId);
        if (team == null) {
            throw new IDNotRecognisedException("Team not found");
        }
        teams.remove(teamId);
    }

    public int[] getTeams() {
        int[] teamIds = new int[teams.size()];
        int index = 0;
        for (int teamID : teams.keySet()) {
            teamIds[index++] = teamID;
        }
        return teamIds;
    }

    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        Team team = teams.get(teamId);
        if (team == null) {
            throw new IDNotRecognisedException("Team not found");
        }
        HashMap<Integer, Rider> riderList = team.getRiders();
        int[] riderIDs = new int[riderList.size()];
        int index = 0;
        for (int riderID : riderList.keySet()) {
            riderIDs[index++] = riderID;
        }
        return riderIDs;

    }

    public int createRider(int teamID, String name, int yearOfBirth)
            throws IDNotRecognisedException, IllegalArgumentException {
        Team team = teams.get(teamID);
        if (team == null) {
            throw new IDNotRecognisedException("Team not found");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rider's name can not be empty");
        }
        if (yearOfBirth < 1900) {
            throw new IllegalArgumentException("Rider's year of birth can not be less than 1900");
        }
        return team.createNewRider(teamID, name, yearOfBirth);

    }

    public void removeRider(int riderId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        int teamID = hf.getTeamIDByRiderID(riderId, teams);
        if (teamID == 0) {
            throw new IDNotRecognisedException("Rider not found in any team");
        }
        Team team = teams.get(teamID);
        team.getRiders().remove(riderId);
    }

    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime[] checkpointTimes)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
            InvalidStageStateException {
        HelperFunction hf = new HelperFunction();
        int teamID = hf.getTeamIDByRiderID(riderId, teams);
        if (teamID == 0){
            throw new IDNotRecognisedException("Rider not found in any team");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId,races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() != "waiting for results") {
                    throw new InvalidStageStateException("Invalid stage state");
                }
            }
        }
        
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getRiderResults().containsKey(riderId)) {
                    throw new DuplicatedResultException("Duplicated result");
                } else {
                    stage.recordRiderResult(riderId, checkpointTimes); 
                    return;
                }
            } else {
                throw new IDNotRecognisedException("ID not recognised");
            }
        
        }

    }

    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return null;
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return null;
    }

    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

    }

    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        return null;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        return null;
    }

    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        return null;
    }

    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
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
