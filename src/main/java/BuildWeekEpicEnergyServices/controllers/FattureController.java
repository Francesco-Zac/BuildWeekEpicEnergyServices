package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Fattura;
import BuildWeekEpicEnergyServices.payloads.NewFatturaDTO;
import BuildWeekEpicEnergyServices.payloads.UpdateFatturaDTO;
import BuildWeekEpicEnergyServices.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FattureController {

    @Autowired
    private FatturaService fatturaService;

    // ▶ CREATE
    @PostMapping
    public Fattura createFattura(@RequestBody @Validated NewFatturaDTO dto) {
        return fatturaService.save(dto);
    }

    // ▶ READ BY ID
    @GetMapping("/{id}")
    public Fattura getFattura(@PathVariable UUID id) {
        return fatturaService.findById(id);
    }

    // ▶ UPDATE
    @PutMapping("/{id}")
    public Fattura updateFattura(@PathVariable UUID id, @RequestBody @Validated UpdateFatturaDTO dto) {
        return fatturaService.update(id, dto);
    }

    // ▶ DELETE
    @DeleteMapping("/{id}")
    public void deleteFattura(@PathVariable UUID id) {
        fatturaService.delete(id);
    }

    // ▶ FILTRI + PAGINAZIONE
    @GetMapping
    public Page<Fattura> getFilteredFatture(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) UUID statoId,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) BigDecimal importoMin,
            @RequestParam(required = false) BigDecimal importoMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy
    ) {
        return fatturaService.findAllFiltered(clienteId, statoId, data, anno, importoMin, importoMax, page, size, sortBy);
    }
}