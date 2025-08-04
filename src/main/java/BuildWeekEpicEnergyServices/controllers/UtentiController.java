package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Utenti;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtentiController {
    private final UtentiServices utentiServices;

    public UtentiController(UtentiServices utentiServices) {
        this.utentiServices = utentiServices;
    }

    @GetMapping
    public List<Utenti> getAll() { return utentiServices.findAll(); }

    @GetMapping("/{id}")
    public Utenti getById(@PathVariable Long id) { return utentiServices.findById(id); }

    @PostMapping
    public Utenti create(@RequestBody Utenti utente) { return utentiServices.create(utente); }

    @PutMapping("/{id}")
    public Utenti update(@PathVariable Long id, @RequestBody Utenti data) {
        return utentiServices.update(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utentiServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}