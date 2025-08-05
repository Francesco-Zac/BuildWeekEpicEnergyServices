package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
   // List<Indirizzo> findByClienteId(Long idCliente);
}
