package BuildWeekEpicEnergyServices.repositories;


import BuildWeekEpicEnergyServices.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByName(String name);
}

