package BuildWeekEpicEnergyServices.repositories;

import BuildWeekEpicEnergyServices.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.math.BigDecimal;
import java.time.LocalDate;



public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    Page<Fattura> findAll(Pageable pageable);


    Page<Fattura> findByCliente_Id(Long clienteId, Pageable pageable);


    Page<Fattura> findByStato_Id(Long statoId, Pageable pageable);


    Page<Fattura> findByData(LocalDate data, Pageable pageable);
    Page<Fattura> findByDataBetween(LocalDate from, LocalDate to, Pageable pageable);

    Page<Fattura> findByImportoBetween(BigDecimal min, BigDecimal max, Pageable pageable);
}
