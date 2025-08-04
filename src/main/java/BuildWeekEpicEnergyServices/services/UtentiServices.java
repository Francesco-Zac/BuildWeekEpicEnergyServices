package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.repositories.UtentiRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class UtentiServices {

    private final UtentiRepository utentiRepository;

    public UtentiServices(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }

    public List<Utente> findAll() {
        return utentiRepository.findAll();
    }

    public Utente findById(Long id) {
        return utentiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Utente create(Utente utenti) {
        return utentiRepository.save(utenti);
    }

    public Utente update(Long id, Utente data) {
        Utente existing = findById(id);
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
