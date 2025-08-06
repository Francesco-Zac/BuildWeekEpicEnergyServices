package BuildWeekEpicEnergyServices.controllers;

import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.entities.Provincia;
import BuildWeekEpicEnergyServices.services.ProvinceComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province-comuni")
public class ProvinceComuniController {
    @Autowired
    private ProvinceComuniService provinceComuniService;

    @GetMapping(params = "type=province")
    public Page<Provincia> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return provinceComuniService.findAllProvince(pageable);
    }

    @GetMapping("/{provinciaId}")
    public Provincia getById(@PathVariable Long id) {
        return provinceComuniService.findByIdProvince(id);
    }


    @GetMapping(params = "type=comuni")
    public Page<Comune> getAllProvince(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return provinceComuniService.findAllComuni(pageable);
    }

    @GetMapping("/{comuneId}")
    public Comune getByIdComuni(@PathVariable Long id) {
        return provinceComuniService.findByIdComuni(id);
    }

    @GetMapping("/comuni/by-provincia")
    public List<Comune> getComuniByProvincia(@RequestParam String sigla) {
        return provinceComuniService.getComuniBySiglaProvincia(sigla);
    }
}
