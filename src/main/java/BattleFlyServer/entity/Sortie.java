package BattleFlyServer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//LOMBOK
@Getter
@Setter
@ToString(exclude = "pilotResults")
@Builder
@NoArgsConstructor
@AllArgsConstructor

//JPA - HIBERNATE
@Entity(name = "Sortie")
public class Sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Sortie_sortieId")
    @SequenceGenerator(name = "Sortie_sortieId", sequenceName = "Sortie_sortieId", allocationSize = 1)
    private int sortieId;

    @OneToMany(mappedBy = "id.sortie", orphanRemoval = true) // for "remove event"
    private List<PilotResult> pilotResults;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    private int sortieNo;
}