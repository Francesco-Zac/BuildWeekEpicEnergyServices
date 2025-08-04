package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Ruoli;
import BuildWeekEpicEnergyServices.services.RuoliServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoliController {
    private final RuoliServices ruoliServices;

    public RuoliController(RuoliServices ruoliServices) {
        this.ruoliServices = ruoliServices;
    }

    @GetMapping
    public List<Ruoli> getAll() { return ruoliServices.findAll(); }

    @GetMapping("/{id}")
    public Ruoli getById(@PathVariable Long id) { return ruoliServices.findById(id); }

    @PostMapping
    public Ruoli create(@RequestBody Ruoli ruolo) { return ruoliServices.create(ruolo); }

    @PutMapping("/{id}")
    public Ruoli update(@PathVariable Long id, @RequestBody Ruoli data) {
        return ruoliServices.update(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruoliServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}