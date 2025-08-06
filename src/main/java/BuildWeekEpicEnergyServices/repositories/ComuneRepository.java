package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
    List<Comune> findByProvinciaSigla(String siglaProvincia);
}
