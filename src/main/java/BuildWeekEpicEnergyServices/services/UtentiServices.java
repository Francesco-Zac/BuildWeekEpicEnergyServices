package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.repositories.UtenteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class UtentiServices {

    private final UtenteRepository utenteRepository;

    public UtentiServices(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Utente create(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente update(Long id, Utente data) {
        Utente existing = findById(id);
        existing.setUsername(data.getUsername());
        existing.setEmail(data.getEmail());
        existing.setNome(data.getNome());
        existing.setCognome(data.getCognome());
        existing.setAvatar(data.getAvatar());
        existing.setRuoli(data.getRuoli());
        return utenteRepository.save(existing);
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }

}
