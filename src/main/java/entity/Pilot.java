package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//LOMBOCK
@Getter
@Setter
@ToString(exclude = "pilotResults")
@Builder
@NoArgsConstructor
@AllArgsConstructor

//JPA - HIBERNATE
@Entity(name = "Pilot")
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Pilot_pilotId") // .IDENTITY or .SEQUENCE??
    @SequenceGenerator(name = "Pilot_pilotId", sequenceName = "Pilot_pilotId", allocationSize = 1)
    private int pilotId;

    @JsonIgnore
    @OneToMany(mappedBy = "id.pilot")
    private List<PilotResult> pilotResults;

    private String name;
}
