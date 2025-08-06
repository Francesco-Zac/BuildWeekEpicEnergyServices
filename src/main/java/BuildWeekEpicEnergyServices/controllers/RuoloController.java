package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.RuoloDTO;
import BuildWeekEpicEnergyServices.services.RuoliServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {

    @Autowired
    private RuoliServices ruoliServices;

    @GetMapping("/{ruoloId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Ruolo getRuoloById(@PathVariable long ruoloId) {
        return ruoliServices.findById(ruoloId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Ruolo> getAllRuoli() {
        return ruoliServices.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo creaRuolo(@RequestBody @Validated RuoloDTO dto, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return this.ruoliServices.create(dto);
    }

    @PutMapping("/{ruoloId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo updateRuolo(@RequestBody @Validated RuoloDTO body, BindingResult validationResult, @PathVariable long ruoloId) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList());
        }
        return this.ruoliServices.update(ruoloId, body);
    }

    @DeleteMapping("/{ruoloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRuolo(@PathVariable long ruoloId) {
        this.ruoliServices.delete(ruoloId);
    }
}
