package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.services.RuoliServices;
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
    public List<Ruolo> getAll() { return ruoliServices.findAll(); }

    @GetMapping("/{id}")
    public Ruolo getById(@PathVariable Long id) { return ruoliServices.findById(id); }

    @PostMapping
    public Ruolo create(@RequestBody Ruolo ruolo) { return ruoliServices.create(ruolo); }

    @PutMapping("/{id}")
    public Ruolo update(@PathVariable Long id, @RequestBody Ruolo data) {
        return ruoliServices.update(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruoliServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}