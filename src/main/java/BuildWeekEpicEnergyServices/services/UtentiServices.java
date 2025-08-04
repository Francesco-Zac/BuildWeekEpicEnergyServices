package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.models.Utenti;
import BuildWeekEpicEnergyServices.repositories.UtentiRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class UtentiServices {

    private final UtentiRepository utentiRepository;

    public UtentiServices(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }

    public List<Utenti> findAll() {
        return utentiRepository.findAll();
    }

    public Utenti findById(Long id) {
        return utentiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Utenti create(Utenti utenti) {
        return utentiRepository.save(utenti);
    }

    public Utenti update(Long id, Utenti data) {
        Utenti existing = findById(id);
        existing.setUsername(data.getUsername());
        existing.setEmail(data.getEmail());
        existing.setNome(data.getNome());
        existing.setCognome(data.getCognome());
        existing.setAvatar(data.getAvatar());
        existing.setRuoli(data.getRuoli());
        return utentiRepository.save(existing);
    }

    public void delete(Long id) {
        utentiRepository.deleteById(id);
    }

}
