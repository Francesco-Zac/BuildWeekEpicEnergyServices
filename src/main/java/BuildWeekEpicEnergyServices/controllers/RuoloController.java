package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.payloads.RuoloDTO;
import BuildWeekEpicEnergyServices.services.RuoliServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {
    private final RuoliServices ruoliServices;

    public RuoloController(RuoliServices ruoliServices) {
        this.ruoliServices = ruoliServices;
    }

    @GetMapping
    public List<Ruolo> getAll() {
        return ruoliServices.findAll();
    }

    @GetMapping("/{id}")
    public Ruolo getById(@PathVariable Long id) {
        return ruoliServices.findById(id);
    }

    @PostMapping
    public Ruolo create(@RequestBody @Valid RuoloDTO ruoloDTO) {
        return ruoliServices.create(ruoloDTO);
    }

    @PutMapping("/{id}")
    public Ruolo update(@PathVariable Long id, @RequestBody @Valid RuoloDTO ruoloDTO) {
        return ruoliServices.update(id, ruoloDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruoliServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
