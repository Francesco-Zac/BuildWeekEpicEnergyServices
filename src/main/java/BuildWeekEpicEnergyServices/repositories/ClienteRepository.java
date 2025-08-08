package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune com JOIN com.provincia p ORDER BY p.provincia ASC")
    Page<Cliente> findAllOrderByProvinciaSedeLegale(Pageable pageable);

    @Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune com JOIN com.provincia p ORDER BY p.provincia DESC")
    Page<Cliente> findAllOrderByProvinciaSedeLegaleDesc(Pageable pageable);

    Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String nomeContatto, Pageable pageable);

    Page<Cliente> findByFatturatoAnnuoGreaterThanEqual(double minFatturato, Pageable pageable);

    Page<Cliente> findByInseritoIlAfter(LocalDate data, Pageable pageable);

    Page<Cliente> findByUltimoContattoIlBefore(LocalDate data, Pageable pageable);

}
