package BattleFlyServer.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

//LOMBOK
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

//JPA-HIBERNATE
@Embeddable
public class PilotResultKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "pilotId", nullable = false)
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "sortieId", nullable = false)
    private Sortie sortie;

    @Override
    public String toString() {
        return "PilotResultKey{" +
                "pilotId=" + pilot.getPilotId() +
                ", sortieId=" + sortie.getSortieId() +
                '}';
    }
}