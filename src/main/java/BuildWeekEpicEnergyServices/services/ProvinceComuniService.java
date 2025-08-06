package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.entities.Provincia;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinceComuniService {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Page<Provincia> findAllProvince(Pageable pageable) {
        return provinciaRepository.findAll(pageable);
    }

    public Provincia findByIdProvince(Long id) {
        return provinciaRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Provincia con ID " + id + " non trovato"));
    }

    public Page<Comune> findAllComuni(Pageable pageable) {
        return comuneRepository.findAll(pageable);
    }

    public Comune findByIdComuni(Long id) {
        return comuneRepository.findById(Long.valueOf(String.valueOf(id)))
                .orElseThrow(() -> new NotFoundException("Comune con ID " + id + " non trovato"));
    }
}
