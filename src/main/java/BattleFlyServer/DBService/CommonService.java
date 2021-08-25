package BattleFlyServer.DBService;

import BattleFlyServer.repository.*;
import BattleFlyServer.supplimentary.*;
import BattleFlyServer.entity.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommonService {

    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    private PilotResultRepository pilotResultRepository;

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private EventRepository eventRepository;

    // event
    public int createEvent(Event event) {// all creation here!!

        try{
            List<ResultStringClass> results = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            String inString = new String(Files.readAllBytes(Paths.get(event.getFileName())));
            List<String> inStrings = Arrays.stream(inString.split("\n")).collect(Collectors.toList());
            for(String ss: inStrings) {
                try{results.add(objectMapper.readValue(ss, ResultStringClass.class));}
                catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            int eventId = eventRepository.save(event).getEventId();
            int sortieNo = -1;
            Sortie sortie = new Sortie();
            Pilot pilot = new Pilot();
            for(ResultStringClass curResult: results){
                Optional<Pilot> unknownPilot = findPilotByName(curResult.getName());
                if (unknownPilot.isPresent()) {
                    pilot=unknownPilot.get();
                } else {
                    pilot = new Pilot();
                    pilot.setName(curResult.getName());
                    pilot = findPilotById(createPilot(pilot));
                }

                if(curResult.getSortieNo() != sortieNo){
                    sortieNo = curResult.getSortieNo();
                    sortie = new Sortie();
                    sortie.setEvent(findEventById(eventId));
                    sortie.setSortieNo(sortieNo);
                    sortie = findSortieById(createSortie(sortie));
                }

                PilotResultKey key = new PilotResultKey(pilot, sortie);
                PilotResult pilotResult = new PilotResult(
                        key
                        ,curResult.getTeam()
                        ,curResult.getRoundsFired()
                        ,curResult.getHitsAchieved()
                        ,curResult.getHitsTaken()
                        ,curResult.getHitsGround()
                        ,curResult.getFfAir()
                        ,curResult.getFfGround()
                        ,curResult.getSoloKills()
                        ,curResult.getKilled()
                        ,curResult.getGroupKills()
                        ,curResult.getKilledBy()
                );
                createPilotResult(pilotResult);
            }
            return eventId;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

       return -1;
    }
    public List<Event> listEvents() {
        return eventRepository.findAll();
    }
    public Event findEventById(Integer id) {
        return eventRepository.findById(id).get();
    }
    public void deleteEvent(Integer id) {eventRepository.deleteById(id);}
    public  List<PilotResult> displayEvent (Integer EventId){
        List<PilotResult> results = new ArrayList<>();
        List<Integer> sorties = new ArrayList<>();
        List<Integer> pilots = new ArrayList<>();
        for(Pilot curPilot: pilotRepository.findAll()) {pilots.add(curPilot.getPilotId());}
        for(Sortie curSortie: sortieRepository.findAll()) {
            if(curSortie.getEvent().getEventId() == EventId){
            sorties.add(curSortie.getSortieId());}
        }
        for(int curSortieId: sorties) {
            for(int curPilotId: pilots) {
                Optional<PilotResult> curResult = resultByKey(curPilotId, curSortieId);
                if (curResult.isPresent()) {
                    results.add(curResult.get());
                }
            }
        }
        return results;
    }

    // sortie (only basics)
    public int createSortie(Sortie sortie) {return sortieRepository.save(sortie).getSortieId();}
    public Sortie findSortieById(Integer id) {return sortieRepository.findById(id).get();}

    // pilot
    public int createPilot(Pilot pilot) {return pilotRepository.save(pilot).getPilotId();}
    public List<Pilot> listPilots() {return pilotRepository.findAll();}
    public Pilot findPilotById(Integer id) {return pilotRepository.findById(id).get();}

    public Optional<Pilot> findPilotByName(String name) {
        List<Pilot> pilots = pilotRepository.findAll();
        for(Pilot curPilot: pilots){
            if(curPilot.getName().equals(name)){return Optional.of(curPilot);}
        }
        return Optional.empty();
    }


    public  List<PilotResult> displayPilot (Integer PilotId){
        List<PilotResult> results = new ArrayList<>();
        List<Integer> sorties = new ArrayList<>();
        for(Sortie curSortie: sortieRepository.findAll()) {sorties.add(curSortie.getSortieId());}
        for(int curSortieId: sorties) {
            Optional<PilotResult> curResult = resultByKey(PilotId, curSortieId);
            if (curResult.isPresent()) {
                results.add(curResult.get());
            }
        }
        return results;
    }

    // pilot result (only basics)
    public PilotResultKey createPilotResult(PilotResult pilotResult) {
       return pilotResultRepository.save(pilotResult).getId();
    }

    public Optional<PilotResult> resultByKey(Integer pilotId, Integer sortieId)
    {
        PilotResultKey key = new PilotResultKey(pilotRepository.findById(pilotId).get()
                ,sortieRepository.findById(sortieId).get());
        return pilotResultRepository.findById(key);
    }
}
