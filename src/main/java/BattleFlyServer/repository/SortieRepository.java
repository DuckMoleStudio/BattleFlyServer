package BattleFlyServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BattleFlyServer.entity.Sortie;

@Repository
public interface SortieRepository extends JpaRepository<Sortie, Integer> {
}
