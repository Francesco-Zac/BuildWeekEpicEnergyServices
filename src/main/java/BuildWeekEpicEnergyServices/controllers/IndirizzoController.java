package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.IndirizzoDTO;
import BuildWeekEpicEnergyServices.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/indirizzi")
public class IndirizzoController {


    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    public Page<Indirizzo> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return indirizzoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Indirizzo getById(@PathVariable Long id) {
        return indirizzoService.findById(id);
    }

    @PostMapping
    public Indirizzo create(@RequestBody Indirizzo indirizzo) {
        return indirizzoService.save(indirizzo);
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)

//    public NewUserRespDTO save(@RequestBody @Validated NewUserDTO payload, BindingResult validationResult) {
//        if (validationResult.hasErrors()) {
//            //validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
//            throw new ValidationException(validationResult.getFieldErrors()
//                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
//        } else {
//            User newUser = this.usersService.save(payload);
//            return new NewUserRespDTO(newUser.getId());
//        }
//    }

    @PutMapping("/{id}")
    public Indirizzo update(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
        return indirizzoService.update(id, indirizzo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        indirizzoService.delete(id);
    }
}
