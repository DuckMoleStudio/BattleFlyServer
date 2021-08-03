package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entity.Pilot;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Integer> {
}