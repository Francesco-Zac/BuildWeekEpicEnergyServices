package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune com JOIN com.provincia p ORDER BY p.provincia ASC")
    Page<Cliente> findAllOrderByProvinciaSedeLegale(Pageable pageable);

    @Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune com JOIN com.provincia p ORDER BY p.provincia DESC")
    Page<Cliente> findAllOrderByProvinciaSedeLegaleDesc(Pageable pageable);

}
