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

import java.util.List;

@Service
public class ProvinceComuniService {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Page<Provincia> findAllProvince(Pageable pageable) {
        return provinciaRepository.findAll(pageable);
    }

    public Provincia findByIdProvince(String sigla) {
        return provinciaRepository.findById(sigla)
                .orElseThrow(() -> new NotFoundException("Provincia non trovata con sigla: " + sigla));
    }

    public Page<Comune> findAllComuni(Pageable pageable) {
        return comuneRepository.findAll(pageable);
    }

    public Comune findByIdComuni(Long id) {
        return comuneRepository.findById(Long.valueOf(String.valueOf(id)))
                .orElseThrow(() -> new NotFoundException("Comune con ID " + id + " non trovato"));
    }

    public List<Comune> getComuniBySiglaProvincia(String sigla) {
        return comuneRepository.findByProvinciaSigla(sigla);
    }
}
