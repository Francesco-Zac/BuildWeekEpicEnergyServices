package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContattoContainingIgnoreCase(String nomeContatto);

    List<Cliente> findByFatturatoAnnuo(double fatturatoAnnuo);

    List<Cliente> findByInseritoIl(LocalDate inseritoIl);

    List<Cliente> findByUltimoContattoIl(LocalDate ultimoContattoIl);


}
