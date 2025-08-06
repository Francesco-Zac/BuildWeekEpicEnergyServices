package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, UUID> {
}
