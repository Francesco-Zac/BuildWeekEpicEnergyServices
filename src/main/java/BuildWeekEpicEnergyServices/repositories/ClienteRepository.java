package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
