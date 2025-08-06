package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    private final UtentiServices utentiServices;

    public UtenteController(UtentiServices utentiServices) {
        this.utentiServices = utentiServices;
    }

    @GetMapping
    public List<Utente> getAll() {
        return utentiServices.findAll();
    }

    @GetMapping("/{id}")
    public Utente getById(@PathVariable Long id) {
        return utentiServices.findById(id);
    }

    @PostMapping
    public Utente create(@RequestBody @Valid UtenteDTO utenteDTO) {
        return utentiServices.create(utenteDTO);
    }

    @PutMapping("/{id}")
    public Utente update(@PathVariable Long id, @RequestBody @Valid UtenteDTO utenteDTO) {
        return utentiServices.update(id, utenteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utentiServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
