package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.repositories.UtenteRepository;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtentiServices {

    private final UtenteRepository utenteRepository;
    private final RuoloRepository ruoloRepository;

    public UtentiServices(UtenteRepository utenteRepository,
                          RuoloRepository ruoloRepository) {
        this.utenteRepository = utenteRepository;
        this.ruoloRepository = ruoloRepository;
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    public Utente create(UtenteDTO dto) {
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setPassword(dto.password());
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setAvatar(dto.avatar());

        Set<Ruolo> ruoli = new HashSet<>(
                ruoloRepository.findAllById(dto.ruoliIds())
        );
        utente.setRuoli(ruoli);

        return utenteRepository.save(utente);
    }

    public Utente update(Long id, UtenteDTO dto) {
        Utente existing = findById(id);
        existing.setUsername(dto.username());
        existing.setEmail(dto.email());
        existing.setPassword(dto.password());
        existing.setNome(dto.nome());
        existing.setCognome(dto.cognome());
        existing.setAvatar(dto.avatar());

        Set<Ruolo> ruoli = new HashSet<>(
                ruoloRepository.findAllById(dto.ruoliIds())
        );
        existing.setRuoli(ruoli);

        return utenteRepository.save(existing);
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }
}
