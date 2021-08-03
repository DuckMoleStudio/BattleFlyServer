package RESTController;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import DBService.CommonService;

import entity.PilotResult;
import entity.PilotResultKey;
import entity.Sortie;
import entity.Event;
import entity.Pilot;


import repository.EventRepository;
import repository.SortieRepository;
import repository.PilotRepository;
import repository.PilotResultRepository;

import java.util.List;

@RestController
@RequestMapping("/pilot")
public class Controller {

    @Autowired
    private CommonService service;

    @GetMapping("/list")
    public List<Pilot> list() {
        return service.list();
    }

    @GetMapping(value = "/pilotbyid/{id}")
    public Pilot findById(@PathVariable("id") Integer id) {
        //return RestPreconditions.checkFound(service.findById(id));
        return service.findById(id);
    }

    @GetMapping(value = "/resultbypilot/{id1}")
    public PilotResult resultById(@PathVariable("id1") Integer id1) {
        return service.resultById(id1);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Pilot pilot) {
        Preconditions.checkNotNull(pilot.getName(),"Name");
        return service.create(pilot);
    }
}
