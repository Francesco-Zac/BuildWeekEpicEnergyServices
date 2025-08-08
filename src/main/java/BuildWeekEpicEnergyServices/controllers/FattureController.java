package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Fattura;
import BuildWeekEpicEnergyServices.payloads.FatturaDTO;
import BuildWeekEpicEnergyServices.payloads.FatturaUpdateDTO;
import BuildWeekEpicEnergyServices.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Fattura createFattura(@RequestBody @Validated FatturaDTO dto) {
        return fatturaService.save(dto);
    }

    // ▶ READ BY ID
    @GetMapping("/{id}")
    public Fattura getFattura(@PathVariable Long id) {
        return fatturaService.findById(id);
    }

    @GetMapping
    public Page<Fattura> getAllFatture(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return fatturaService.getAllFatture(page, size, sortBy, direction);
    }

    @GetMapping("/by-cliente")
    public Page<Fattura> getFattureByCliente(
            @RequestParam Long clienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return fatturaService.getFattureByCliente(clienteId, page, size);
    }

    @GetMapping("/by-stato")
    public Page<Fattura> getFattureByStato(
            @RequestParam long statoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return fatturaService.getFattureByStatoId(statoId, page, size);
    }

    @GetMapping("/by-data")
    public Page<Fattura> getFattureByData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return fatturaService.getFattureByData(data, page, size);
    }

    @GetMapping("/by-anno")
    public Page<Fattura> getFattureByAnno(
            @RequestParam int anno,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return fatturaService.getFattureByAnno(anno, page, size);
    }


    @GetMapping("/by-importo")
    public Page<Fattura> getFattureByImportoRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return fatturaService.getFattureByImportoRange(min, max, page, size);
    }

    // ▶ UPDATE
    @PutMapping("/{id}")
    public Fattura updateFattura(@PathVariable Long id, @RequestBody @Validated FatturaUpdateDTO dto) {
        return fatturaService.update(id, dto);
    }

    // ▶ DELETE
    @DeleteMapping("/{id}")
    public void deleteFattura(@PathVariable Long id) {
        fatturaService.delete(id);
    }


}