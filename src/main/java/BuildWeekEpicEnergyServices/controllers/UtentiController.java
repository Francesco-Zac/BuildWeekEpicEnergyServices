package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Utente;
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
    public List<Utente> getAll() { return utentiServices.findAll(); }

    @GetMapping("/{id}")
    public Utente getById(@PathVariable Long id) { return utentiServices.findById(id); }

    @PostMapping
    public Utente create(@RequestBody Utente utente) { return utentiServices.create(utente); }

    @PutMapping("/{id}")
    public Utente update(@PathVariable Long id, @RequestBody Utente data) {
        return utentiServices.update(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utentiServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}