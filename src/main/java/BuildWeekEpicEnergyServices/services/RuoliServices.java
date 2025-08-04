package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.models.Ruoli;
import BuildWeekEpicEnergyServices.repositories.RuoliRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RuoliServices {
    private final RuoliRepository ruoliRepository;

    public RuoliServices(RuoliRepository ruoliRepository) {
        this.ruoliRepository = ruoliRepository;
    }

    public List<Ruoli> findAll() {
        return ruoliRepository.findAll();
    }

    public Ruoli findById(Long id) {
        return ruoliRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }

    public Ruoli create(Ruoli ruolo) {
        return ruoliRepository.save(ruolo);
    }

    public Ruoli update(Long id, Ruoli data) {
        Ruoli existing = findById(id);
        existing.setName(data.getName());  // Lombok ha generato setName/getName su `name`
        return ruoliRepository.save(existing);
    }

    public void delete(Long id) {
        ruoliRepository.deleteById(id);
    }
}
