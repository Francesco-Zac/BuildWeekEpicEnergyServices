package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Comune, Long> {
}
