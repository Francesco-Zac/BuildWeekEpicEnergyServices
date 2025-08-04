package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {
    Optional<Provincia> findByProvincia(String provincia);
}
