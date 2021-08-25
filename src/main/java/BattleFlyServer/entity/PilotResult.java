package BattleFlyServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

//LOMBOK
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

//JPA - HIBERNATE
@Entity(name = "PilotResult")
public class PilotResult {

    @JsonIgnore
    @EmbeddedId
    private PilotResultKey id;

    private int team, roundsFired, hitsAchieved, hitsTaken, hitsGround, ffAir, ffGround,
            soloKills, killed, groupKills;
    String killedBy;
}