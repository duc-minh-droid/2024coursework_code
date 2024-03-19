
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
    private static HashMap<Integer, Race> races = new HashMap<Integer, Race>();
    private static HashMap<Integer, Team> teams = new HashMap<Integer, Team>();

    public static void main(String[] args) throws InvalidNameException, IllegalNameException, IDNotRecognisedException,
            InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException,
            DuplicatedResultException, InvalidCheckpointTimesException, IOException, ClassNotFoundException,
            NameNotRecognisedException {

        CyclingPortal cp = new CyclingPortalImpl();
        int TourDeFrancID = cp.createRace("TourDeFranc", "GO GO GO GO");
        int TourDeVietID = cp.createRace("TourDeViet", "DI DI DI DI");
        int TourDeUKID = cp.createRace("TourDeUK", "Oy oy oy oy");
        int[] raceIDs = cp.getRaceIds();

        System.out.println("Printing IDs of every race:");
        for (int id : raceIDs) {
            System.out.print(id);
        }
        System.out.println("=====================================");

        System.out.println("Printing race details: \n");
        cp.viewRaceDetails(TourDeFrancID);
        System.out.println("=====================================");

        System.out.println("Deleting race TourDeVietID...");
        cp.removeRaceById(TourDeVietID);
        System.out.println(races.get(TourDeVietID));
        System.out.println("TourDeVietID race deleted!");
        System.out.println("=====================================");

        System.err.println("Adding stages to TouDeFranc");
        int stage1ID = cp.addStageToRace(TourDeFrancID, "COCO", "desc", 150.0, null, StageType.FLAT);
        System.out.println("Added stage with ID: " + stage1ID + " to TourDeFranc");
        int stage2ID = cp.addStageToRace(TourDeFrancID, "ConCo", "desc", 200.0, null, StageType.HIGH_MOUNTAIN);
        System.out.println("Added stage with ID: " + stage2ID + " to TourDeFranc");
        int stage3ID = cp.addStageToRace(TourDeFrancID, "ConCon", "desc", 200.0, null, StageType.MEDIUM_MOUNTAIN);
        System.out.println("Added stage with ID: " + stage3ID + " to TourDeFranc");
        System.out.println("=====================================");

        System.out.println("Number of stages: " + cp.getNumberOfStages(TourDeFrancID));
        System.out.println("=====================================");

        System.err.println("Printing IDs of each stage in TourDeFranc");
        int[] TdfStages = cp.getRaceStages(TourDeFrancID);
        for (int i : TdfStages) {
            System.out.println(i);
        }
        System.out.println("=====================================");

        System.out.println("First stage's length: " + cp.getStageLength(stage1ID));
        System.out.println("=====================================");

        System.out.println("Deleting third stage");
        cp.removeStageById(stage3ID);
        System.out.println("Number of stages after deletion: " + races.get(TourDeFrancID).getNumberOfStages());
        System.out.println("Third stage deleted");
        System.out.println("=====================================");

        System.out.println("Adding checkpoints to first stage");
        int climb1Stage1 = cp.addCategorizedClimbToStage(stage1ID, 6.0, CheckpointType.C2, 6.0, 6.0);
        System.out.println("Added climb checkpoint with ID: " + climb1Stage1 + " to first stage");
        int climb2Stage1 = cp.addCategorizedClimbToStage(stage1ID, 15.0, CheckpointType.C4, 6.0, 6.0);
        System.out.println("Added climb checkpoint with ID: " + climb2Stage1 + " to first stage");
        int climb3Stage1 = cp.addCategorizedClimbToStage(stage1ID, 30.0, CheckpointType.HC, 6.0, 6.0);
        System.out.println("Added climb checkpoint with ID: " + climb3Stage1 + " to first stage");
        int sprint1Stage1 = cp.addIntermediateSprintToStage(stage1ID, 6.0);
        System.out.println("Added sprint checkpoint with ID: " + sprint1Stage1 + " to first stage");
        System.out.println("=====================================");

        System.out.println("Adding checkpoints to second stage");
        int climb1Stage2 = cp.addCategorizedClimbToStage(stage2ID, 6.0, CheckpointType.C2, 6.0, 6.0);
        System.out.println("Added climb checkpoint with ID: " + climb1Stage2 + " to 2nd stage");
        int climb2Stage2 = cp.addCategorizedClimbToStage(stage2ID, 30.0, CheckpointType.HC, 6.0, 6.0);
        System.out.println("Added climb checkpoint with ID: " + climb2Stage2 + " to 2nd stage");
        int sprint1Stage2 = cp.addIntermediateSprintToStage(stage2ID, 15.0);
        System.out.println("Added sprint checkpoint with ID: " + sprint1Stage2 + " to 2nd stage");
        int sprint2Stage2 = cp.addIntermediateSprintToStage(stage2ID, 30.0);
        System.out.println("Added sprint checkpoint with ID: " + sprint2Stage2 + " to 2nd stage");
        System.out.println("=====================================");

        System.out.println("Deleting third climb checkpoint in stage 1...");
        System.out.println(
                "Number of checkpoints in stage 1 before deletion: " + cp.getStageCheckpoints(stage1ID).length);
        cp.removeCheckpoint(climb3Stage1);
        System.out
                .println("Number of checkpoints in stage 1 after deletion: " + cp.getStageCheckpoints(stage1ID).length);
        System.out.println("=====================================");

        System.out.println("Concluding stage preparation for stage 1...");
        cp.concludeStagePreparation(stage1ID);
        System.out.println("Concluding stage preparation for stage 2...");
        cp.concludeStagePreparation(stage2ID);
        for (Stage stage : races.get(TourDeFrancID).getStages()) {
            if (stage.getStageID() == stage1ID) {
                System.out.println("Stage 1's state: " + stage.getStageState());
            } else if (stage.getStageID() == stage2ID) {
                System.out.println("Stage 2's state: " + stage.getStageState());
            }
        }
        System.out.println("=====================================");

        System.out.println("Creating teams...");
        int teamSkyID = cp.createTeam("Sky", "British");
        System.out.println("Added team with ID: " + teamSkyID);
        int teamRedBullID = cp.createTeam("RedBull", "Wings");
        System.out.println("Added team with ID: " + teamRedBullID);
        int teamFerrariID = cp.createTeam("Rari", "Charles Leclerc");
        System.out.println("Added team with ID: " + teamRedBullID);
        System.out.println("=====================================");

        System.out.println("Printing teams' ID...");
        int[] teamList = cp.getTeams();
        for (int t : teamList) {
            System.out.println("Team's ID: " + t);
        }
        System.out.println("=====================================");

        System.out.println("Deleting team team Ferrari...");
        System.out.println("Number of team before deletion: " + cp.getTeams().length);
        cp.removeTeam(teamFerrariID);
        System.out.println("Number of team after deletion: " + cp.getTeams().length);
        System.out.println("=====================================");

        System.out.println("Creating riders for team Sky...");
        int johnSkyId = cp.createRider(teamSkyID, "John", 1989);
        System.out.println("Created rider with ID: " + johnSkyId + "in the team Sky");
        int adamSkyId = cp.createRider(teamSkyID, "Adam", 1997);
        System.out.println("Created rider with ID: " + adamSkyId + "in the team Sky");
        int DanSkyId = cp.createRider(teamSkyID, "Dan", 2000);
        System.out.println("Created rider with ID: " + DanSkyId + "in the team Sky");
        System.out.println("=====================================");

        System.out.println("Creating riders for team RedBull...");
        int millerRBId = cp.createRider(teamRedBullID, "Miller", 2010);
        System.out.println("Created rider with ID: " + millerRBId + "in the team RedBull");
        int feinRBId = cp.createRider(teamSkyID, "Fein", 1990);
        System.out.println("Created rider with ID: " + feinRBId + "in the team RedBull");
        System.out.println("=====================================");

        // System.out.println("Removing Dan from team Sky...");
        // System.out.println("Number of riders in team Sky before deletion: " + cp.getTeamRiders(teamSkyID).length);
        // cp.removeRider(DanSkyId);
        // System.out.println("Number of riders in team Sky after deletion: " + cp.getTeamRiders(teamSkyID).length);
        // System.out.println("=====================================");

        LocalTime[] johnStage1Times = { LocalTime.of(7, 30, 0), LocalTime.of(7, 40, 0), LocalTime.of(8, 0, 0),
                LocalTime.of(8, 30, 0), LocalTime.of(9, 0, 0) };
        LocalTime[] adamStage1Times = { LocalTime.of(7, 30, 0), LocalTime.of(7, 50, 4), LocalTime.of(8, 11, 0),
                LocalTime.of(8, 47, 0), LocalTime.of(9, 15, 0) };
        LocalTime[] millerStage1Times = { LocalTime.of(7, 30, 0), LocalTime.of(8, 11, 0), LocalTime.of(8, 32, 0),
                LocalTime.of(8, 46, 0), LocalTime.of(9, 10, 0) };
        LocalTime[] feinStage1Times = { LocalTime.of(7, 30, 0), LocalTime.of(8, 32, 0), LocalTime.of(8, 48, 0),
                LocalTime.of(9, 0, 0), LocalTime.of(9, 20, 0) };
        LocalTime[] danStage1Times = { LocalTime.of(7, 30, 0), LocalTime.of(8, 32, 0), LocalTime.of(8, 48, 0),
                LocalTime.of(9, 0, 0), LocalTime.of(9, 20, 0) };

        System.out.println("Registering John's results in stage 1...");
        cp.registerRiderResultsInStage(stage1ID, johnSkyId, johnStage1Times);
        System.out.println("Registering Adam's results in stage 1...");
        cp.registerRiderResultsInStage(stage1ID, adamSkyId, adamStage1Times);
        System.out.println("Registering Miller's results in stage 1...");
        cp.registerRiderResultsInStage(stage1ID, millerRBId, millerStage1Times);
        System.out.println("Registering Fein's results in stage 1...");
        cp.registerRiderResultsInStage(stage1ID, feinRBId, feinStage1Times);
        System.out.println("Registering dan's results in stage 1...");
        cp.registerRiderResultsInStage(stage1ID, DanSkyId, danStage1Times);
        System.out.println("=====================================");

        LocalTime[] johnStage2Times = { LocalTime.of(6, 20, 0), LocalTime.of(7, 20, 0), LocalTime.of(8, 00, 0),
                LocalTime.of(8, 35, 0), LocalTime.of(9, 0, 0), LocalTime.of(9, 30, 0) };
        LocalTime[] adamStage2Times = { LocalTime.of(6, 20, 0), LocalTime.of(7, 40, 4), LocalTime.of(8, 21, 0),
                LocalTime.of(8, 47, 0), LocalTime.of(9, 10, 0), LocalTime.of(9, 45, 0) };
        LocalTime[] millerStage2Times = { LocalTime.of(6, 20, 0), LocalTime.of(7, 50, 0), LocalTime.of(8, 32, 0),
                LocalTime.of(8, 46, 0), LocalTime.of(9, 30, 0), LocalTime.of(9, 50, 0) };
        LocalTime[] feinStage2Times = { LocalTime.of(6, 20, 0), LocalTime.of(7, 55, 0), LocalTime.of(8, 28, 0),
                LocalTime.of(8, 50, 0), LocalTime.of(9, 35, 0), LocalTime.of(9, 50, 0, 250) };
        LocalTime[] danStage2Times = { LocalTime.of(6, 20, 0), LocalTime.of(7, 55, 0), LocalTime.of(8, 28, 0),
            LocalTime.of(8, 50, 0), LocalTime.of(9, 35, 0), LocalTime.of(9, 50, 0, 250) };
    

        System.out.println("Registering John's results in stage 2...");
        cp.registerRiderResultsInStage(stage2ID, johnSkyId, johnStage2Times);
        System.out.println("Registering Adam's results in stage 2...");
        cp.registerRiderResultsInStage(stage2ID, adamSkyId, adamStage2Times);
        System.out.println("Registering Miller's results in stage 2...");
        cp.registerRiderResultsInStage(stage2ID, millerRBId, millerStage2Times);
        System.out.println("Registering Fein's results in stage 2...");
        cp.registerRiderResultsInStage(stage2ID, feinRBId, feinStage2Times);
        System.out.println("Registering dan's results in stage 2...");
        cp.registerRiderResultsInStage(stage2ID, DanSkyId, danStage2Times);
        System.out.println("=====================================");

        System.out.println("Removing Dan from team Sky...");
        System.out.println("Number of riders in team Sky before deletion: " + cp.getTeamRiders(teamSkyID).length);
        cp.removeRider(DanSkyId);
        System.out.println("Number of riders in team Sky after deletion: " + cp.getTeamRiders(teamSkyID).length);
        System.out.println("=====================================");

        
        System.out.println("Printing John's results in stage 1...");
        LocalTime[] johnResultsStage1 = cp.getRiderResultsInStage(stage1ID, johnSkyId);
        for (LocalTime lt : johnResultsStage1) {
            System.out.println(lt);
        }
        System.out.println("Printing John's results in stage 2...");
        LocalTime[] johnResultsStage2 = cp.getRiderResultsInStage(stage2ID, johnSkyId);
        for (LocalTime lt : johnResultsStage2) {
            System.out.println(lt);
        }
        System.out.println("=====================================");
        
        System.out.println("Printing Fein's adjusted elapsed time in stage 2...");
        System.out.println(cp.getRiderAdjustedElapsedTimeInStage(stage2ID, feinRBId));
        System.out.println("Printing Miller's adjusted elapsed time in stage 2...");
        System.out.println(cp.getRiderAdjustedElapsedTimeInStage(stage2ID, millerRBId));
        System.out.println("=====================================");
        
        System.out.println("Deleting Miller's result in stage 1");
        cp.deleteRiderResultsInStage(stage1ID, millerRBId);
        System.out.println("=====================================");

        System.out.println("Printing riders' rank in stage 1...");
        int[] ridersRankStage1 = cp.getRidersRankInStage(stage1ID);
        for (int rank : ridersRankStage1) {
            System.out.println(
                    cp.getRiderResultsInStage(stage1ID, rank)[cp.getRiderResultsInStage(stage1ID, rank).length - 1]);
            System.out.println("Rider: "
                    + teams.get(HelperFunction.getTeamIDByRiderID(rank, teams)).getRiders().get(rank).getRiderName());
            System.out.println("Rider's IDs: " + rank);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' adjusted elapsed times in stage 2...");
        LocalTime[] ridersAdjustedRankStage2 = cp.getRankedAdjustedElapsedTimesInStage(stage2ID);
        for (LocalTime time : ridersAdjustedRankStage2) {
            System.out.println("Rider's time: " + time);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' points in stage 1...");
        int[] ridersPointStage1 = cp.getRidersPointsInStage(stage1ID);
        for (int points : ridersPointStage1) {
            System.out.println("Rider's points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' points in stage 2...");
        int[] ridersPointStage2 = cp.getRidersPointsInStage(stage2ID);
        for (int points : ridersPointStage2) {
            System.out.println("Rider's points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' mountain points in stage 1...");
        int[] ridersMountainPointStage1 = cp.getRidersMountainPointsInStage(stage1ID);
        for (int points : ridersMountainPointStage1) {
            System.out.println("Rider's mountain points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' mountain points in stage 2...");
        int[] ridersMountainPointStage2 = cp.getRidersMountainPointsInStage(stage2ID);
        for (int points : ridersMountainPointStage2) {
            System.out.println("Rider's mountain points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Race number before deletion: " + races.size());
        System.out.println("Deleting TourDeUK by name");
        cp.removeRaceByName("TourDeUK");
        System.out.println("Race number after deletion: " + races.size());
        System.out.println("=====================================");

        System.out.println("Printing Riders General Classification Rank: ");
        int[] ridersGCRank = cp.getRidersGeneralClassificationRank(TourDeFrancID);
        for (int rank : ridersGCRank) {
            System.out.println("Rider: "
                    + teams.get(HelperFunction.getTeamIDByRiderID(rank, teams)).getRiders().get(rank).getRiderName());
            System.out.println("Rider's IDs: " + rank);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' time in TourDeFranc...");
        LocalTime[] ridersGCTimes = cp.getGeneralClassificationTimesInRace(TourDeFrancID);
        for (LocalTime time : ridersGCTimes) {
            System.out.println("Rider's time: " + time);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' points in TourDeFranc...");
        int[] ridersPointRace = cp.getRidersPointsInRace(TourDeFrancID);
        for (int points : ridersPointRace) {
            System.out.println("Rider's points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Printing riders' mountain points in TourDeFranc...");
        int[] ridersMountainPointRace = cp.getRidersMountainPointsInRace(TourDeFrancID);
        for (int points : ridersMountainPointRace) {
            System.out.println("Rider's mountain points: " + points);
        }
        System.out.println("=====================================");

        System.out.println("Printing Riders Point Classification Rank: ");
        int[] ridersPCRank = cp.getRidersPointClassificationRank(TourDeFrancID);
        for (int rank : ridersPCRank) {
            System.out.println("Rider: "
                    + teams.get(HelperFunction.getTeamIDByRiderID(rank, teams)).getRiders().get(rank).getRiderName());
            System.out.println("Rider's IDs: " + rank);
        }
        System.out.println("=====================================");

        System.out.println("Printing Riders Mountain Point Classification Rank: ");
        int[] ridersMPCRank = cp.getRidersMountainPointClassificationRank(TourDeFrancID);
        for (int rank : ridersMPCRank) {
            System.out.println("Rider: "
                    + teams.get(HelperFunction.getTeamIDByRiderID(rank, teams)).getRiders().get(rank).getRiderName());
            System.out.println("Rider's IDs: " + rank);
        }
        System.out.println("=====================================");

        System.out.println("Saving cycling portal in Portal1.ser...");
        cp.saveCyclingPortal("Portal1.ser");
        System.out.println("=====================================");

        System.out.println("Erasing races in cycling portal...");
        System.out.println("Number of races before deletion: " + races.size());
        cp.eraseCyclingPortal();
        System.out.println("Number of races after deletion: " + races.size());
        System.out.println("=====================================");
        System.out.println("Loading Cycling Portal from Portal1.ser...");
        cp.loadCyclingPortal("Portal1.ser");
        System.out.println("Number of races after loading Cycling Portal: " + races.size());
        System.out.println("=====================================");

        for(int i : HelperFunction.getStageByID(stage1ID, races).ridersTotalPointsInStage.keySet()){
            System.out.println(HelperFunction.getStageByID(stage1ID, races).ridersTotalPointsInStage.get(i));
        }
    }

    public int[] getRaceIds() {
        return Race.getRaceIds(races);
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

        if (HelperFunction.getRacesNames(races).contains(name)) {
            throw new IllegalNameException(("Race's name already existing"));
        }

        return Race.createRace(name, description, races);
    }

    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        if (!races.keySet().contains(raceId)) {
            throw new IDNotRecognisedException("Race not found");
        }

        Race race = races.get(raceId);

        // create a fomarted string containing details of the race
        String details = "Race's ID: " + race.getRaceId() + "\n" +
                "Name: " + race.getName() + "\n" +
                "Description: " + race.getDescription() + "\n" +
                "Number of Stage: " + getNumberOfStages(raceId) + "\n" +
                "Length: " + race.getLength() + "\n";
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

        if (HelperFunction.getStagesNames(races).contains(stageName)) {
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

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }

        Stage stage = HelperFunction.getStageByID(stageId, races);
        return stage.getLength();
    }

    public void removeStageById(int stageId) throws IDNotRecognisedException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }
        int raceID = HelperFunction.getRaceIDByStageID(stageId, races);
        ArrayList<Stage> stages = races.get(raceID).getStages();
        // Use an iterator to remove the stage
        Iterator<Stage> iterator = stages.iterator();
        while (iterator.hasNext()) {
            Stage stage = iterator.next();
            if (stage.getStageID() == stageId) {
                iterator.remove(); // Remove the stage using the iterator
                break;
            }
        }
        
    }

    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage not found");
        }
        boolean locationIsInvalid = false;
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
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
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
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
        if (!StageTypes.contains(HelperFunction.getStageByID(stageId, races).getType())) {
            throw new InvalidStageTypeException("Invalid stage type");
        }

        Stage stage = HelperFunction.getStageByID(stageId, races);
        return stage.addCategorizedClimbToStage(location, type, averageGradient, length);
    }

    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        boolean locationIsInvalid = false;
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
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
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
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
        if (!StageTypes.contains(HelperFunction.getStageByID(stageId, races).getType())) {
            throw new InvalidStageTypeException("Invalid stage type");
        }
        if (false) {
            throw new InvalidStageTypeException("Invalid stage type");
        }

        Stage stage = HelperFunction.getStageByID(stageId, races);
        return stage.addIntermediateSprintToStage(location);
    }

    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {

        if (!HelperFunction.getAllCheckpoints(races).contains(checkpointId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByCheckpointID(checkpointId, races)).getStages()) {
            if (stage.getStageID() == HelperFunction.getStageIDByCheckpointID(checkpointId, races)) {
                if (stage.getStageState() == "waiting for results") {
                    throw new InvalidStageStateException("Invalid stage state");
                }
            }
        }
        int stageID = HelperFunction.getStageIDByCheckpointID(checkpointId, races);
        ArrayList<Stage> stages = races.get(HelperFunction.getRaceIDByCheckpointID(checkpointId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageID) {
                ArrayList<Checkpoint> checkpoints = stage.getCheckpoints();
                Iterator<Checkpoint> iterator = checkpoints.iterator();
                while (iterator.hasNext()) {
                    Checkpoint checkpoint = iterator.next();
                    if (checkpoint.getCheckpointID() == checkpointId) {
                        iterator.remove(); // Remove the checkpoint using the iterator
                        break;
                    }
                }
            }
        }
    }

    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        boolean stageIsInvalid = false;
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() == "waiting for results") {
                    stageIsInvalid = true;
                }
            }
        }
        if (stageIsInvalid) {
            throw new InvalidStageStateException("Invalid stage state");
        }
        ArrayList<Stage> stages = races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                stage.concludeStagePreparation();
            }
        }
    }

    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("ID not recognised");
        }
        ArrayList<Stage> stages = races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages();
        for (Stage stage : stages) {
            if (stage.getStageID() == stageId) {
                return stage.getStageCheckpoints();
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

        if (HelperFunction.getTeamsNames(teams).contains(name)) {
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

        if (!teams.keySet().contains(teamID)) {
            throw new IDNotRecognisedException("Team not found");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rider's name can not be empty");
        }
        if (yearOfBirth < 1900) {
            throw new IllegalArgumentException("Rider's year of birth can not be less than 1900");
        }

        Team team = teams.get(teamID);
        return team.createNewRider(teamID, name, yearOfBirth);

    }

    public void removeRider(int riderId) throws IDNotRecognisedException {

        int teamID = HelperFunction.getTeamIDByRiderID(riderId, teams);
        if (!teams.keySet().contains(teamID) || teamID == 0) {
            throw new IDNotRecognisedException("Rider not found");
        }

        Team team = teams.get(teamID);
        
        for (Race race : races.values()) {
            LinkedHashMap<Integer, Integer> riderTMPR = race.getRidersTotalMountainPointsInRace();
            riderTMPR.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
            
            LinkedHashMap<Integer, Integer> riderTPR = race.getRidersTotalPointsInRace();
            riderTPR.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
            
            for (Stage stage : race.getStages()) {
                HashMap<Integer, LocalTime[]> riderRS = stage.getRiderResults();
                riderRS.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
                
                HashMap<Integer, ArrayList<StageTime>> riderORS = stage.getRiderObjectResults();
                riderORS.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
                
                LinkedHashMap<Integer, LocalTime> ridersAET = stage.getRidersAdjustedElapsedTimes();
                ridersAET.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
                
                LinkedHashMap<Integer, LocalTime> ridersET = stage.getRidersElapsed();
                ridersET.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
                
                LinkedHashMap<Integer, Integer> ridersTMPS = stage.getRidersTotalMountainPointsInStage();
                ridersTMPS.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
                
                LinkedHashMap<Integer, Integer> ridersTPS = stage.getRidersTotalPointsInStage();
                ridersTPS.entrySet().removeIf(entry -> entry.getKey().equals(riderId));
            }
        }
        team.getRiders().remove(riderId);
        
    }

    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime[] checkpointTimes)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
            InvalidStageStateException {

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        int teamID = HelperFunction.getTeamIDByRiderID(riderId, teams);
        if (teamID == 0) {
            throw new IDNotRecognisedException("Rider not found in any team");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getStageState() != "waiting for results") {
                    throw new InvalidStageStateException("Invalid stage state");
                }
            }
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (checkpointTimes.length != stage.getNumberOfCheckpoints() + 2) {
                    throw new InvalidCheckpointTimesException("Invalid number of check points time");
                }
            }
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
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

        if (!HelperFunction.getStagesIDs(races).contains(stageId)) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        if (!HelperFunction.getRidersIDs(teams).contains(riderId)) {
            throw new IDNotRecognisedException("Rider's ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                if (stage.getRiderResults().containsKey(riderId)) {
                    return stage.getRiderResultsInStage(riderId);
                }
            }
        }
        LocalTime[] lt = {};// only return checkpoint time and elapsed time
        return lt;
    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {

        if (HelperFunction.getRiderByID(riderId, teams) == null) {
            throw new IDNotRecognisedException("Rider's ID not recognised");
        }
        if (HelperFunction.getStageByID(stageId, races) == null) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRiderAdjustedElapsedTimeInStage(riderId);
            }
        }
        return null;

    }

    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // After deleting the rider's results, do we still conclude his results after the race? 
        // If we ignore his result in one stage, the time system won't work as intended as he will take less time than others
        // One method that we have planned, is to remove the rider result in every stage to not interfere with the point and time system but that would change the functionality of the method.
        boolean isRiderIDWrong = false;
        boolean isStageIDWrong = false;
        if (HelperFunction.getRiderByID(riderId, teams) == null) {
            isRiderIDWrong = true;
        }
        if (HelperFunction.getStageByID(stageId, races) == null){
            isStageIDWrong = true;
        }

        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                stage.removeRiderResults(riderId);
            // } else {
            //     isStageIDWrong = true;
            }
        }
        if (isRiderIDWrong)
            throw new IDNotRecognisedException("Rider's ID not recognised");
        if (isStageIDWrong)
            throw new IDNotRecognisedException("Stage's ID not recognised");

    }
    

    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {

        if (HelperFunction.getStageByID(stageId, races) == null) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRidersRankInStage();
            }
        }
        return null;
    }

    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {

        if (HelperFunction.getStageByID(stageId, races) == null) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }

        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRankedAdjustedElapsedTimesInStage(stageId, races);
            }
        }
        return null;
    }

    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {

        if (HelperFunction.getStageByID(stageId, races) == null) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRidersPoints();
            }
        }
        return null;
    }

    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {

        if (HelperFunction.getStageByID(stageId, races) == null) {
            throw new IDNotRecognisedException("Stage's ID not recognised");
        }
        for (Stage stage : races.get(HelperFunction.getRaceIDByStageID(stageId, races)).getStages()) {
            if (stage.getStageID() == stageId) {
                return stage.getRidersMountainPoints();
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
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in loading the file");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Class file not found");
        }
    }

    public void removeRaceByName(String name) throws NameNotRecognisedException {
        Iterator<Map.Entry<Integer, Race>> iterator = races.entrySet().iterator();
        boolean isDone = false;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Race> entry = iterator.next();
            Race race = entry.getValue();

            if (race.getName().equals(name)) {
                iterator.remove(); // Remove the entry using the iterator
                isDone = true;
            }
        }
        if (!isDone)
            throw new NameNotRecognisedException("Name not recognised");

    }

    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getGeneralClassificationTimesInRace();
    }

    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getRidersPointsInRace();
    }

    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getRidersMountainPointsInRace();
    }

    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getRidersGeneralClassificationRank();
    }

    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getRidersPointClassificationRank();
    }

    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        Race race = races.get(raceId);
        if (race == null) {
            throw new IDNotRecognisedException("Race not found");
        }
        return race.getRidersMountainPointClassificationRank();
    }

}
