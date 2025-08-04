package BuildWeekEpicEnergyServices.repositories;


import BuildWeekEpicEnergyServices.entities.Ruoli;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RuoliRepository extends JpaRepository<Ruoli, Long> {
    Optional<Ruoli> findByName(String name);
}

