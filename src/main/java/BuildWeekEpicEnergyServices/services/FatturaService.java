package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Fattura;
import BuildWeekEpicEnergyServices.entities.StatoFattura;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.FatturaDTO;
import BuildWeekEpicEnergyServices.payloads.FatturaUpdateDTO;
import BuildWeekEpicEnergyServices.repositories.ClienteRepository;
import BuildWeekEpicEnergyServices.repositories.FatturaRepository;
import BuildWeekEpicEnergyServices.repositories.StatoFatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public Fattura save(FatturaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.cliente().getId()).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        StatoFattura statoFattura = statoFatturaRepository.findById(dto.statoFattura().getId()).orElseThrow(() -> new NotFoundException("Stato non trovato"));

        Fattura f = new Fattura();
        f.setData(dto.data());
        f.setImporto(dto.importo());
        f.setCliente(cliente);
        f.setStatoFattura(statoFattura);

        return fatturaRepository.save(f);

    }

    public Fattura findById(Long id) {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }

    public Fattura update(Long id, FatturaUpdateDTO dto) {
        Fattura existing = fatturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata con id: " + id));


        if (dto.data() == null) {
            throw new IllegalArgumentException("La data non può essere null");
        }

        if (dto.importo() == null || dto.importo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("L'importo deve essere positivo");
        }


        Cliente cliente = clienteRepository.findById(dto.cliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato con id: " + dto.cliente().getId()));


        StatoFattura stato = statoFatturaRepository.findById(dto.statoFattura().getId())
                .orElseThrow(() -> new EntityNotFoundException("Stato fattura non trovato con id: " + dto.statoFattura().getId()));


        existing.setCliente(cliente);
        existing.setStatoFattura(stato);
        existing.setData(dto.data());
        existing.setImporto(dto.importo());

        return fatturaRepository.save(existing);
    }

    public void delete(Long id) {
        Fattura f = findById(id);
        fatturaRepository.delete(f);
    }


    public Page<Fattura> getAllFatture(int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        pageable = PageRequest.of(page, size, sort);
        return fatturaRepository.findAll(pageable);
    }


    public Page<Fattura> getFattureByCliente(Long clienteId, int page, int size) {
        return fatturaRepository.findByCliente_Id(clienteId, PageRequest.of(page, size));
    }


    public Page<Fattura> getFattureByStatoId(long statoId, int page, int size) {
        return fatturaRepository.findByStato_Id(statoId, PageRequest.of(page, size));
    }

    public Page<Fattura> getFattureByData(LocalDate data, int page, int size) {
        return fatturaRepository.findByData(data, PageRequest.of(page, size));
    }


    public Page<Fattura> getFattureByAnno(int anno, int page, int size) {
        LocalDate from = LocalDate.of(anno, 1, 1);
        LocalDate to   = LocalDate.of(anno, 12, 31);
        return fatturaRepository.findByDataBetween(from, to, PageRequest.of(page, size));
    }


    public Page<Fattura> getFattureByImportoRange(BigDecimal min, BigDecimal max, int page, int size) {
        return fatturaRepository.findByImportoBetween(min, max, PageRequest.of(page, size));
    }

}
