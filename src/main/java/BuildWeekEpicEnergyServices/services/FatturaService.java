package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Fattura;
import BuildWeekEpicEnergyServices.entities.StatoFattura;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.FatturaSpecification;
import BuildWeekEpicEnergyServices.payloads.NewFatturaDTO;
import BuildWeekEpicEnergyServices.payloads.UpdateFatturaDTO;
import BuildWeekEpicEnergyServices.repositories.ClienteRepository;
import BuildWeekEpicEnergyServices.repositories.FatturaRepository;
import BuildWeekEpicEnergyServices.repositories.StatoFatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public Fattura save(NewFatturaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        StatoFattura statoFattura = statoFatturaRepository.findById(dto.statoId()).orElseThrow(() -> new NotFoundException("Stato non trovato"));

        Fattura f = new Fattura();
        f.setData(dto.data());
        f.setImporto(dto.importo());
        f.setCliente(cliente);
        f.setStatoFattura(statoFattura);

        return fatturaRepository.save(f);

    }

    public Fattura findById(UUID id) {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }

    public Fattura update(UUID id, UpdateFatturaDTO dto) {
        Fattura esistente = findById(id);

        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new NotFoundException("Cliente non trovato"));

        StatoFattura statoFattura = statoFatturaRepository.findById(dto.statoId()).orElseThrow(() -> new NotFoundException("Stato non trovato"));

        esistente.setData(dto.data());
        esistente.setImporto(dto.importo());
        esistente.setNumero(dto.numero());
        esistente.setCliente(cliente);
        esistente.setStatoFattura(statoFattura);

        return fatturaRepository.save(esistente);

    }

    public void delete(UUID id) {
        Fattura f = findById(id);
        fatturaRepository.delete(f);
    }

    public Page<Fattura> findAllFiltered(Long clienteId, UUID statoId, LocalDate data, Integer anno, BigDecimal importoMin, BigDecimal importoMax, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Specification<Fattura> spec = FatturaSpecification.byFilters(clienteId, statoId, data, anno, importoMin, importoMax);
        return fatturaRepository.findAll(spec, pageable);
    }

}
