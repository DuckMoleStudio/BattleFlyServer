package DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class CommonService { @Autowired
private PilotRepository repository;

    @Autowired
    private PilotResultRepository repository2;

    @Autowired
    private SortieRepository repository3;

    public List<Pilot> list() {
        return repository.findAll();
    }

    public int create(Pilot pilot) {
        return repository.save(pilot).getPilotId();
    }

    public Pilot findById(Integer id) {
        return repository.findById(id).get();
    }

    public PilotResult resultById(Integer pilotId)
    {
        PilotResultKey key2 = new PilotResultKey(repository.findById(pilotId).get(),repository3.findById(1).get());

        return repository2.findById(key2).get();
    }
}
