package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Utente> getAll() {
        return utentiServices.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Utente getById(@PathVariable Long id) {
        return utentiServices.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Utente create(@RequestBody @Valid UtenteDTO utenteDTO) {
        return utentiServices.create(utenteDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Utente update(@PathVariable Long id, @RequestBody @Valid UtenteDTO utenteDTO) {
        return utentiServices.update(id, utenteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utentiServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
