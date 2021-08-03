package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//LOMBOK
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sorties") //need we?

//JPA - HIBERNATE
@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="Event_eventId")
    @SequenceGenerator(name = "Event_eventId", sequenceName="Event_eventId", allocationSize = 1)
    private int eventId;

    @OneToMany(mappedBy="event", orphanRemoval = true) // for "remove event"
    private List<Sortie> sorties;

    private String name;
    private String location;
    private Date eventDate;
    private String eventStatus;

    @Override
    public String toString() { // need we?
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", eventStatus=" + eventStatus +
                '}';
    }
}