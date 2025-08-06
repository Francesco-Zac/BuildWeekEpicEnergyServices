package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.NuovoClienteDTO;
import BuildWeekEpicEnergyServices.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClientiService clientiService;

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Cliente getClienteById(@PathVariable long clienteId) {
        return clientiService.findById(clienteId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Cliente> getAllClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ragioneSociale") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return clientiService.getAllClienti(page, size, sortBy, direction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente creaCliente(@RequestBody @Validated NuovoClienteDTO dto, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            return this.clientiService.creaCliente(dto);
        }
    }

    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateCliente(@RequestBody @Validated NuovoClienteDTO body, BindingResult validationResult, @PathVariable long clienteId) {
        if(validationResult.hasErrors()) {
            throw new ValidationException((validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList()));
        } else {
            return this.clientiService.findByIdAndUpdate(clienteId, body);
        }
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getClienteByIdAndDelete(@PathVariable long clienteId) {
        this.clientiService.findByIdAndDelete(clienteId);
    }

    @PatchMapping("/{clienteId}/logo_azienda")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String uploadImage(@PathVariable long clienteId, @RequestParam("profileImage") MultipartFile file) {
        return this.clientiService.uploadLogo(clienteId, file);
    }

}

