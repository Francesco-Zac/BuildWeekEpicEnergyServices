package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface FatturaRepository extends JpaRepository<Fattura, UUID>, JpaSpecificationExecutor<Fattura> {
}
