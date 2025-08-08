package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.payloads.UtenteUpdateDTO;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtentiServices utentiServices;
    @Autowired
    private RuoloRepository ruoloRepository;

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
        Ruolo ruoloUser = ruoloRepository.findByName("USER")
                .orElseGet(() -> {
                    System.out.println("Ruolo USER non trovato, lo creo...");
                    return ruoloRepository.save(new Ruolo("USER"));
                });
        return this.utentiServices.create(dto, Set.of(ruoloUser));
    }

    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateUtente(@RequestBody @Validated UtenteUpdateDTO body, BindingResult validationResult, @PathVariable long utenteId) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return this.utentiServices.updateDTO(utenteId, body);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUtente(@PathVariable long utenteId) {
        this.utentiServices.delete(utenteId);
    }

    @GetMapping("/me")
    public Utente getOwnProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente) {
        return currentAuthenticatedUtente;
    }



}
