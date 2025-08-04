package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Ruolo;
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

    public Ruolo create(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    public Ruolo update(Long id, Ruolo data) {
        Ruolo existing = findById(id);
        existing.setName(data.getName());  // Lombok ha generato setName/getName su `name`
        return ruoloRepository.save(existing);
    }

    public void delete(Long id) {
        ruoloRepository.deleteById(id);
    }
}
