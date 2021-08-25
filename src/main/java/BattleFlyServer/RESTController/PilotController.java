package BattleFlyServer.RESTController;

import BattleFlyServer.DBService.CommonService;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import BattleFlyServer.entity.*;

import java.util.List;

@RestController
@RequestMapping("/pilot")
public class PilotController {

    @Autowired
    private CommonService service;

    @GetMapping("/list")
    public List<Pilot> list() {
        return service.listPilots();
    }

    /*
    @GetMapping(value = "/pilotbyid/{id}")
    public Pilot findById(@PathVariable("id") Integer id) {
        //return RestPreconditions.checkFound(service.findById(id));
        return service.findPilotById(id);
    }

     */

    @GetMapping(value = "/resultbypilot/{id}")
    public List<PilotResult> resultById(@PathVariable("id") Integer id) {
        return service.displayPilot(id);
    }
/*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Pilot pilot) {
        Preconditions.checkNotNull(pilot.getName(),"Name");
        return service.createPilot(pilot);
    }

 */
}
