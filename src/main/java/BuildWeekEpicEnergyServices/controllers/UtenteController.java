package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtentiServices utentiServices;

    @GetMapping("/{utenteId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Utente getUtenteById(@PathVariable long utenteId) {
        return utentiServices.findById(utenteId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Utente> getAllUtenti() {
        return utentiServices.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente creaUtente(@RequestBody @Validated UtenteDTO dto, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return this.utentiServices.create(dto);
    }

    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateUtente(@RequestBody @Validated UtenteDTO body, BindingResult validationResult, @PathVariable long utenteId) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return this.utentiServices.update(utenteId, body);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUtente(@PathVariable long utenteId) {
        this.utentiServices.delete(utenteId);
    }
}
