
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class CyclingPortalImpl implements CyclingPortal {
    private static  HashMap<Integer, Race> races = new HashMap<Integer, Race>();
    private  HashMap<Integer, Team> teams = new HashMap<Integer, Team>();

  
    public static void main(String[] args) throws InvalidNameException, IllegalNameException, IDNotRecognisedException,
            InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException, DuplicatedResultException, InvalidCheckpointTimesException, IOException, ClassNotFoundException {

        CyclingPortal cp = new CyclingPortalImpl();
        int LeMansID = cp.createRace("LeMans", "GO GO GO GO");
        // // int[] ids = cp.getRaceIds();
        // // for (int id : ids) {
        // //     // System.out.print(id);
        // // }
        int LeBonkID = cp.addStageToRace(LeMansID, "COCO", "desc", 50.0, null,  StageType.FLAT);
        int LeDussyID = cp.addStageToRace(LeMansID, "ConCo", "desc", 200.0, null, StageType.FLAT);
        // int LeHoeID = cp.addStageToRace(LeMansID, "ConCon", "desc", 6.0, null, StageType.FLAT);
        // int[] stageIDs = cp.getRaceStages(LeMansID);
        // // cp.removeStageById(LeBonkID);
        // // System.out.println(cp.getNumberOfStages(LeMansID));
        // // for (int stageID: cp.getRaceStages(LeMansID)) {
        // // System.out.println(stageID);
        // // }
        int LeMonkcpID = cp.addCategorizedClimbToStage(LeDussyID, 6.0, CheckpointType.C1, 6.0, 6.0);
        int LeSpermcpID = cp.addCategorizedClimbToStage(LeDussyID, 15.0, CheckpointType.C1, 6.0, 6.0);
        int LeMonkkcpID = cp.addCategorizedClimbToStage(LeDussyID, 30.0, CheckpointType.C1, 6.0, 6.0);

        int LeMonk1cpID = cp.addCategorizedClimbToStage(LeBonkID, 6.0, CheckpointType.C1, 6.0, 6.0);
        int LeSperm1cpID = cp.addCategorizedClimbToStage(LeBonkID, 15.0, CheckpointType.C1, 6.0, 6.0);
        int LeMonkk1cpID = cp.addCategorizedClimbToStage(LeBonkID, 30.0, CheckpointType.C1, 6.0, 6.0);
        
        // int LeStonkcpID = cp.addIntermediateSprintToStage(LeDussyID, 6.0);
        // int LeStonkID1 = cp.addIntermediateSprintToStage(LeDussyID, 15.0);
        // int LeStonkID2 = cp.addIntermediateSprintToStage(LeDussyID, 30.0);

        // int LeStonkcpID3 = cp.addIntermediateSprintToStage(LeBonkID, 6.0);
        // int LeStonkID4 = cp.addIntermediateSprintToStage(LeBonkID, 15.0);
        // int LeStonkID5 = cp.addIntermediateSprintToStage(LeBonkID, 30.0);
        // for (Stage stage : races.get(LeMansID).getStages()) {
        // // if (stage.getStageID() == LeDussyID) {
        // // for (Checkpoint chepoi : stage.getCheckpoints()) {
        // // System.out.println(chepoi.getCheckpointID());
        // // }

        // // }
        // // }
        // // cp.removeCheckpoint(LeMonkcpID);
        // // int[] cpIDs = cp.getStageCheckpoints(LeDussyID);
        // // for (int id : cpIDs) {
        // //     // System.out.println(id);
        // // }
        int Lakers = cp.createTeam("laker", "desc");
        int Lakersss = cp.createTeam("rekal", "desc");
        int BronnyID = cp.createRider(Lakers, "Bronny", 2000);
        int B = cp.createRider(Lakers, "James Harden", 1999);
        int C = cp.createRider(Lakers, "Luka Doncic", 1999);
        int D = cp.createRider(Lakers, "Giannis Antettokumpo", 1999);
        int E = cp.createRider(Lakers, "Lamelo Ball", 1999);

        // // // cp.removeRider(B);
        // // // cp.removeTeam(Lakers);
        // // // // cp.getTeamRiders(Lakers);
        // // // int[] lakersRiders = cp.getTeamRiders(Lakers);
        // // // System.out.println("Riders of Lakers:");
        // // // for (int riderId : lakersRiders) {
        // // //     System.out.println(riderId);
        // // // }

        // LocalTime[] now = {LocalTime.of(12, 0, 1),LocalTime.now(),LocalTime.now() };
        // LocalTime[] now1 = {LocalTime.of(12, 0, 0, 500_000_000),LocalTime.now(),LocalTime.now() };
        // LocalTime[] now2 = {LocalTime.of(12, 0, 0,750),LocalTime.now(),LocalTime.now() };
        // LocalTime[] now3 = {LocalTime.of(11, 0, 0),LocalTime.now(),LocalTime.now() };


        // cp.concludeStagePreparation(LeBonkID);
        // cp.registerRiderResultsInStage(LeBonkID,BronnyID, now );
        // cp.registerRiderResultsInStage(LeBonkID, B, now1 );
        // cp.registerRiderResultsInStage(LeBonkID, C, now2 );
        // cp.registerRiderResultsInStage(LeBonkID, D, now3 );

        LocalTime[] LeDussyID1 = {LocalTime.of(7, 30, 0),LocalTime.of(7, 40, 0),LocalTime.of(8, 0, 0),LocalTime.of(8, 30, 0),LocalTime.of(9, 0,0) };
        LocalTime[] LeDussyID2 = {LocalTime.of(7, 30, 0),LocalTime.of(7, 50, 4),LocalTime.of(8, 11, 0),LocalTime.of(8, 47, 0),LocalTime.of(9,15 ,0) };
        LocalTime[] LeDussyID3 = {LocalTime.of(7, 30, 0),LocalTime.of(8, 11, 0),LocalTime.of(8, 32, 0),LocalTime.of(8,46, 0),LocalTime.of(9, 10,0) };
        LocalTime[] LeDussyID4 = {LocalTime.of(7, 30, 0),LocalTime.of(8, 32, 0),LocalTime.of(8, 48, 0),LocalTime.of(9, 0, 0),LocalTime.of(9, 20,0) };

        cp.concludeStagePreparation(LeDussyID);
        cp.registerRiderResultsInStage(LeDussyID, BronnyID, LeDussyID1);
        cp.registerRiderResultsInStage(LeDussyID, B, LeDussyID2 );
        cp.registerRiderResultsInStage(LeDussyID, C, LeDussyID3 );
        cp.registerRiderResultsInStage(LeDussyID, D,LeDussyID4 );

        LocalTime[] LeBonkID1 = {LocalTime.of(6, 20, 0),LocalTime.of(7, 20, 0),LocalTime.of(8, 00, 0),LocalTime.of(8, 35, 0),LocalTime.of(9, 0,0) };
        LocalTime[] LeBonkID2 = {LocalTime.of(6, 20, 0),LocalTime.of(7, 40, 4),LocalTime.of(8, 21, 0),LocalTime.of(8, 47, 0),LocalTime.of(9, 20,0) };
        LocalTime[] LeBonkID3 = {LocalTime.of(6, 20, 0),LocalTime.of(7, 50, 0),LocalTime.of(8, 32, 0),LocalTime.of(8,46, 0),LocalTime.of(9, 30,0)};
        LocalTime[] LeBonkID4 = {LocalTime.of(6, 20, 0),LocalTime.of(7, 55, 0),LocalTime.of(8, 28, 0),LocalTime.of(8, 50, 0),LocalTime.of(9, 35,0)};

        cp.concludeStagePreparation(LeBonkID);
        cp.registerRiderResultsInStage(LeBonkID, BronnyID, LeBonkID1);
        cp.registerRiderResultsInStage(LeBonkID, B, LeBonkID2 );
        cp.registerRiderResultsInStage(LeBonkID, C, LeBonkID3 );
        cp.registerRiderResultsInStage(LeBonkID, D,LeBonkID4 );
        


        
        int[] mp = cp.getRidersMountainPointsInRace(LeMansID);
        for(int m : mp){
            System.out.println("points: " + m);
        }
        // LocalTime[] gct = cp.getGeneralClassificationTimesInRace(LeMansID);
        // for(LocalTime m : gct){
        //     System.out.println("times: " + m);
        // }

        // LocalTime[] LeHoeID1 = {LocalTime.of(3, 30, 1),LocalTime.now(),LocalTime.now() };
        // LocalTime[] LeHoeID2 = {LocalTime.of(3, 30, 0, 500_000_000),LocalTime.now(),LocalTime.now() };
        // LocalTime[] LeHoeID3 = {LocalTime.of(3, 0, 0,750),LocalTime.now(),LocalTime.now() };
        // LocalTime[] LeHoeID4 = {LocalTime.of(3, 0, 0),LocalTime.now(),LocalTime.now() };

        // cp.concludeStagePreparation(LeHoeID);
        // cp.registerRiderResultsInStage(LeHoeID,BronnyID, LeHoeID1 );
        // cp.registerRiderResultsInStage(LeHoeID, B, LeHoeID2 );
        // cp.registerRiderResultsInStage(LeHoeID, C, LeHoeID3 );
        // cp.registerRiderResultsInStage(LeHoeID, D, LeHoeID4 );

        // for(HashMap j : cp.getTeamRiders(Lakers)){
        //     System.out.print()
        // }
        // LocalTime[] lts = cp.getGeneralClassificationTimesInRace(LeMansID);
        // int[] ids = cp.getRidersGeneralClassificationRank(LeMansID);
        // for (int i = 0; i < lts.length; i++) {
        //     System.out.println("Rider's ID: " + ids[i]);
        //     System.out.println(lts[i]);
        //     System.out.println();
        // }


        // cp.getRiderAdjustedElapsedTimeInStage( LeBonkID, D); 
        
        // for (LocalTime lt : cp.getRiderResultsInStage(LeBonkID, BronnyID)) {
        //     // System.out.println(lt);
        // }
        //  LocalTime Lee = cp.getRiderAdjustedElapsedTimeInStage(LeDussyID, B);
        //  LocalTime Lee1 = cp.getRiderAdjustedElapsedTimeInStage(LeBonkID, B);
        //  LocalTime Lee2 = cp.getRiderAdjustedElapsedTimeInStage(LeHoeID, B);
        //  System.out.println("Time in stage by " + B + " time : " + Lee);
        //  System.out.println("Time in stage " + Lee1);
        //  System.out.println("Time in stage " + Lee2);
        //  System.out.println();
        //  System.out.println();

        //  LocalTime L = cp.getRiderAdjustedElapsedTimeInStage(LeDussyID, C);
        //  LocalTime L1 = cp.getRiderAdjustedElapsedTimeInStage(LeBonkID, C);
        //  LocalTime L2 = cp.getRiderAdjustedElapsedTimeInStage(LeHoeID, C);
        //  System.out.println("Time in stage by " + C + " time : " +L);
        //  System.out.println("Time in stage " +L1);
        //  System.out.println("Time in stage " + L2); 
        //  System.out.println();
         
        //  LocalTime A = cp.getRiderAdjustedElapsedTimeInStage(LeDussyID, D);
        //  LocalTime A1 = cp.getRiderAdjustedElapsedTimeInStage(LeBonkID, D);
        //  LocalTime A2 = cp.getRiderAdjustedElapsedTimeInStage(LeHoeID, D);
        //  System.out.println("Time in stage by " + D + " time : " +A);
        //  System.out.println("Time in stage " +A1);
        //  System.out.println("Time in stage " + A2);  
        //  System.out.println();

        //  LocalTime r = cp.getRiderAdjustedElapsedTimeInStage(LeDussyID, BronnyID);
        //  LocalTime r1 = cp.getRiderAdjustedElapsedTimeInStage(LeBonkID, BronnyID);
        //  LocalTime r2 = cp.getRiderAdjustedElapsedTimeInStage(LeHoeID, BronnyID);
        //  System.out.println("Time in stage by " + BronnyID + " time : " +r);
        //  System.out.println("Time in stage " +r1);
        //  System.out.println("Time in stage " + r2);  
        //  System.out.println();
         
        // for (int i : cp.getRidersRankInStage(LeBonkID)){
        //     System.out.println(i);}
        // for (LocalTime i : cp.getRankedAdjustedElapsedTimesInStage(LeBonkID)){
        //     //  System.out.println(i);
        //     }
        

        
        // cp.loadCyclingPortal("Portal5.ser");
        // cp.eraseCyclingPortal();
        // for (int id : races.keySet()) {
        //     System.out.println(races.get(id).getName());
        // }
    }

    public int[] getRaceIds() {
        return Race.getRaceIds(races);
    }

    public  int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
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

        return Race.createRace(name, description, races);
    }

    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        if (!races.keySet().contains(raceId)) {
            throw new IDNotRecognisedException("Race not found");
        }
        
        Race race = races.get(raceId);
        
        //create a fomarted string containing details of the race
        String details = "Race's ID: "       + race.getRaceId()         + "\n" +
        "Name: "            + race.getName()           + "\n" +
        "Description: "     + race.getDescription()    + "\n" +
        "Number of Stage: " + getNumberOfStages(raceId) + "\n" +
        "Length: "          + race.getLength()         + "\n";
        return details;
    }

    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        if (!races.keySet().contains(raceId)) {
            throw new IDNotRecognisedException("Race not found");
        }

        races.remove(raceId);
    }

    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        if (!races.keySet().contains(raceId)) {
            throw new IDNotRecognisedException("Race not found");
        }

        Race race = races.get(raceId);
        return race.getNumberOfStages();
    }

    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
    
        if (!races.keySet().contains(raceId)) {
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

        Race race = races.get(raceId);
        return race.addStageToRace(raceId, stageName, description, length, startTime, type);
    }

    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        if (!races.keySet().contains(raceId)) {
            throw new IDNotRecognisedException("Race not found");
        }
        
       Race race = races.get(raceId);
       return race.getRaceStages();
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
        // StageTypes.add(StageType.TT);
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
        ArrayList<StageType> StageTypes = new ArrayList<StageType>();
        StageTypes.add(StageType.FLAT);
        StageTypes.add(StageType.MEDIUM_MOUNTAIN);
        StageTypes.add(StageType.HIGH_MOUNTAIN);
        // StageTypes.add(StageType.TT);
        if (!StageTypes.contains(hf.getStageByID(stageId,races).getType())) {
            throw new InvalidStageTypeException("Invalid stage type");
        }
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

        return Team.createTeam(name, description, teams);
    }

    public void removeTeam(int teamId) throws IDNotRecognisedException {
        if (!teams.keySet().contains(teamId)) {
            throw new IDNotRecognisedException("Team not found");
        }

        teams.remove(teamId);
    }

    public int[] getTeams() {
        return Team.getTeams(teams);
    }

    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        if (!teams.keySet().contains(teamId)) {
            throw new IDNotRecognisedException("Team not found");
        }

        Team team = teams.get(teamId);
        return team.getTeamRiders();

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
        if (!hf.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
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
                if (checkpointTimes.length != stage.getNumberOfCheckpoints() + 2){
                    throw new InvalidCheckpointTimesException("Invalid number of check points time");
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
            } 
        
        }

    }

    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        if (!hf.getRidersIDs(teams).contains(riderId)) {
            throw new IDNotRecognisedException("Rider's ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getRiderResults().containsKey(riderId)) {
                    LocalTime[] result = stage.getRiderResults().get(riderId);
                    LocalTime[] lt = {};
                    for (int i = 0; i < result.length; i++) {
                        if (i == 0 || i == result.length-2) {
                            continue;
                        } else {
                            Arrays.copyOf(lt, lt.length+1);
                            lt[lt.length] = result[i];
                        }
                    }
                } else {
                    throw new IDNotRecognisedException("Rider's ID not recognised"); // need to throw an exception for empty result
                }
            } else {
                throw new IDNotRecognisedException("Stage's ID not recognised");
            
            }
        }
        LocalTime[] lt = {};// only return checkpoint time and elapsed time
        return lt;
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (hf.getRiderByID(riderId, teams) == null){
            throw new IDNotRecognisedException("Rider's ID not recognised");
        }
        if (hf.getStageByID(stageId, races) == null){
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRiderAdjustedElapsedTimeInStage(riderId);
            }
        }
        return null;

    }

    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()){
            if (stage.getStageID() == stageId){
                if (stage.getRiderResults().containsKey(riderId)){
                    stage.getRiderResults().remove(riderId);
                } else {
                    throw new IDNotRecognisedException("Rider's ID not recognised");
                }
            } else {
                throw new IDNotRecognisedException("Stage's ID not recognised");
            }
    }}

    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (hf.getStageByID(stageId, races) == null){
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()) {
                if (stage.getStageID() == stageId){
                    return stage.getRidersRankInStage(races);}
                }
            return null;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (hf.getStageByID(stageId, races) == null){
            throw new IDNotRecognisedException("Stage's ID not recognised");}
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()){
                if (stage.getStageID() == stageId){
                    return stage.getRankedAdjustedElapsedTimesInStage(stageId, races);
                }
            }
        return null;
    }

    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (hf.getStageByID(stageId, races) == null){
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()){
                if (stage.getStageID() == stageId){
                    return stage.getRidersPoints(races);
                }
            }
        return null;
    }

    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        if (hf.getStageByID(stageId, races) == null){
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(hf.getRaceIDByStageID(stageId, races)).getStages()){
                if (stage.getStageID() == stageId){
                    return stage.getRidersMountainPoints(races);
                }
            }
        return null;
    }

    public void eraseCyclingPortal() {
        races.clear();
        teams.clear(); 
    }

    public void saveCyclingPortal(String filename) throws IOException {
        
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            SerializedHashMap shm = new SerializedHashMap(races, teams);
            System.out.println(races.size());
            objectOut.writeObject(shm);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in saving the file");
        }
    }

    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            SerializedHashMap shm = (SerializedHashMap) objectIn.readObject();
            races = shm.getRaces();
            teams = shm.getTeams();
            objectIn.close();
            fileIn.close();
        } catch (IOException e ) {
            e.printStackTrace();
            throw new IOException("Error in loading the file");
        } catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class file not found");
        }
    }

    public void removeRaceByName(String name) throws NameNotRecognisedException {
        for (Race race : races.values()) {
            if (race.getName().equals(name)) {
                races.remove(race.getRaceId());
            } else {
                throw new NameNotRecognisedException("Name not recognised");
            }
        }

    }

    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        HashMap<Integer, LocalTime> riderTotalAdjustedElapsedTimes = new HashMap<Integer, LocalTime>();

        // Loop through each stage and find the accumulated Adjusted Elapsed Times for each rider
        for (Stage stage : races.get(raceId).getStages()) {
            for (int riderID : stage.getRidersAdjustedElapsedTimes().keySet()) {
                // Return an empty array if there is no result for any stage in the race 
                for (int riderIDcheck : riderTotalAdjustedElapsedTimes.keySet()) {
                    if (!stage.getRidersAdjustedElapsedTimes().containsKey(riderIDcheck)) {
                        LocalTime[] emptyArray = {};
                        return emptyArray;
                    }
                }
                if (riderTotalAdjustedElapsedTimes.containsKey(riderID)) {
                    riderTotalAdjustedElapsedTimes.put(riderID, hf.sumTwoLocalTime(riderTotalAdjustedElapsedTimes.get(riderID), stage.getRidersAdjustedElapsedTimes().get(riderID)));
                    for (Rider rider :teams.get(HelperFunction.getTeamIDByRiderID(riderID, teams)).getRiders().values()) {
                        if (rider.getRiderID() == riderID) {
                            System.out.println(rider.getRiderName() + " received " + hf.sumTwoLocalTime(riderTotalAdjustedElapsedTimes.get(riderID), stage.getRidersAdjustedElapsedTimes().get(riderID)));
                        }
                    }

                } else {
                    riderTotalAdjustedElapsedTimes.put(riderID, stage.getRidersAdjustedElapsedTimes().get(riderID));
                    for (Rider rider :teams.get(HelperFunction.getTeamIDByRiderID(riderID, teams)).getRiders().values()) {
                        if (rider.getRiderID() == riderID) {
                            System.out.println(rider.getRiderName() + " received " + stage.getRidersAdjustedElapsedTimes().get(riderID));
                        }
                    }
                }
            }
        }

        // Sort the entries in descending order of LocalTime values
        ArrayList<Map.Entry<Integer, LocalTime>> sortedEntries = new ArrayList<>(riderTotalAdjustedElapsedTimes.entrySet());
        Collections.sort(sortedEntries, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

        // Extract the keys into the GCrank array
        LocalTime[] GCtimes = new LocalTime[sortedEntries.size()];
        for (int i = 0; i < sortedEntries.size(); i++) {
            GCtimes[i] = sortedEntries.get(i).getValue();
        }
        return GCtimes;
    }

    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        int[] ridersRaceTotalPoints = {};
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }

        // Loop through each rider ID in GC rank and add up their total point of each stage
        for(int riderID : getRidersGeneralClassificationRank(raceId)){ 
            int totalRiderPoints = 0;
            for (Stage stage : race.getStages()){
                HashMap<Integer, Integer> ridersTotalPointsInStage = stage.getRidersTotalPointsInStage(races);
                if (ridersTotalPointsInStage.keySet().contains(riderID)){
                    totalRiderPoints += ridersTotalPointsInStage.get(riderID);               
                }else {
                    int[] emptyArray = {};
                    return emptyArray;
                 }
                } 
            
        ridersRaceTotalPoints = Arrays.copyOf(ridersRaceTotalPoints, ridersRaceTotalPoints.length + 1);
        ridersRaceTotalPoints[ridersRaceTotalPoints.length -1 ] = totalRiderPoints;
        race.putRidersTotalPointsInRace(riderID, totalRiderPoints);
            }
        return ridersRaceTotalPoints;    
    }   
    

    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        int[] ridersRaceTotalMountainPoints = {};
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }

        // Loop through each rider ID in GC rank and add up their total point of each stage
        for(int riderID : getRidersGeneralClassificationRank(raceId)){

            int totalRiderPoints = 0;
            for (Stage stage : race.getStages()){
                HashMap<Integer, Integer> ridersTotalMountainPointsInStage = stage.getRidersTotalMountainPointsInStage(races);
                    if (ridersTotalMountainPointsInStage.containsKey(riderID)){
                        totalRiderPoints += ridersTotalMountainPointsInStage.get(riderID);        
                    }else {
                        int[] emptyArray = {};
                        return emptyArray;
                    }
            }
            ridersRaceTotalMountainPoints = Arrays.copyOf(ridersRaceTotalMountainPoints, ridersRaceTotalMountainPoints.length + 1);
            ridersRaceTotalMountainPoints[ridersRaceTotalMountainPoints.length -1 ] = totalRiderPoints;
            race.putRidersTotalMountainPointsInRace(riderID, totalRiderPoints);
            
        }
        return ridersRaceTotalMountainPoints;  
    }
    
    

    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        HelperFunction hf = new HelperFunction();
        HashMap<Integer, LocalTime> riderTotalAdjustedElapsedTimes = new HashMap<Integer, LocalTime>();

        // Loop through each stage and find the accumulated Adjusted Elapsed Times for each rider
        for (Stage stage : races.get(raceId).getStages()) {
            for (int riderID : stage.getRidersAdjustedElapsedTimes().keySet()) {
                // Return an empty array if there is no result for any stage in the race 
                for (int riderIDcheck : riderTotalAdjustedElapsedTimes.keySet()) {
                    if (!stage.getRidersAdjustedElapsedTimes().containsKey(riderIDcheck)) {
                        int[] emptyArray = {};
                        return emptyArray;
                    }
                }
                if (riderTotalAdjustedElapsedTimes.containsKey(riderID)) {
                    riderTotalAdjustedElapsedTimes.put(riderID, hf.sumTwoLocalTime(riderTotalAdjustedElapsedTimes.get(riderID), stage.getRidersAdjustedElapsedTimes().get(riderID)));
                } else {
                    riderTotalAdjustedElapsedTimes.put(riderID, stage.getRidersAdjustedElapsedTimes().get(riderID));
                }
            }
        }

        // Sort the entries in descending order of LocalTime values
        ArrayList<Map.Entry<Integer, LocalTime>> sortedEntries = new ArrayList<>(riderTotalAdjustedElapsedTimes.entrySet());
        Collections.sort(sortedEntries, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

        // Extract the keys into the GCrank array
        int[] GCrank = new int[sortedEntries.size()];
        for (int i = 0; i < sortedEntries.size(); i++) {
            GCrank[i] = sortedEntries.get(i).getKey();
            for (Rider rider :teams.get(HelperFunction.getTeamIDByRiderID(sortedEntries.get(i).getKey(), teams)).getRiders().values()) {
                if (rider.getRiderID() == sortedEntries.get(i).getKey()) {
                    System.out.println(rider.getRiderName());
                }
            }
        }

        return GCrank;
    }

    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        LinkedHashMap<Integer, Integer> ridersTotalPointsInRace = race.getRidersTotalPointsInRace();
        int[] sortedKeys = HelperFunction.sortHashMapByValues(ridersTotalPointsInRace);
        return sortedKeys;
    }

    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        LinkedHashMap<Integer, Integer> ridersTotalPointsInRace = race.getRidersTotalMountainPointsInRace();
        int[] sortedKeys = HelperFunction.sortHashMapByValues(ridersTotalPointsInRace);
        return sortedKeys;
    }

    

}
