package BattleFlyServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BattleFlyServer.entity.PilotResult;
import BattleFlyServer.entity.PilotResultKey;

@Repository
public interface PilotResultRepository extends JpaRepository<PilotResult, PilotResultKey> {
}