package BattleFlyServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BattleFlyServer.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
