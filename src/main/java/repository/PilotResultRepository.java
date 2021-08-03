package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entity.PilotResult;
import entity.PilotResultKey;

@Repository
public interface PilotResultRepository extends JpaRepository<PilotResult, PilotResultKey> {
}