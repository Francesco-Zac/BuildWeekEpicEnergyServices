package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.payloads.RuoloDTO;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoliServices {
    private final RuoloRepository ruoloRepository;

    public RuoliServices(RuoloRepository ruoloRepository) {
        this.ruoloRepository = ruoloRepository;
    }

    public List<Ruolo> findAll() {
        return ruoloRepository.findAll();
    }

    public Ruolo findById(Long id) {
        return ruoloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }

    public Ruolo create(RuoloDTO dto) {
        Ruolo ruolo = new Ruolo();
        ruolo.setName(dto.name());
        return ruoloRepository.save(ruolo);
    }

    public Ruolo update(Long id, RuoloDTO dto) {
        Ruolo existing = findById(id);
        existing.setName(dto.name());
        return ruoloRepository.save(existing);
    }

    public void delete(Long id) {
        ruoloRepository.deleteById(id);
    }
}
