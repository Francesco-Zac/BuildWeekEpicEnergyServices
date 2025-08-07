package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.exceptions.BadRequestException;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.payloads.UtenteUpdateDTO;
import BuildWeekEpicEnergyServices.repositories.UtenteRepository;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UtentiServices {

    private final UtenteRepository utenteRepository;
    private final RuoloRepository ruoloRepository;

    @Autowired
    private PasswordEncoder bCrypt;

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

//    public Utente create(UtenteDTO dto) {
//        Utente utente = new Utente();
//        utente.setUsername(dto.username());
//        utente.setEmail(dto.email());
//        utente.setPassword(bCrypt.encode(dto.password()));
//        utente.setNome(dto.nome());
//        utente.setCognome(dto.cognome());
//
//        return utenteRepository.save(utente);
//    }

    public Utente create(UtenteDTO dto, Set<Ruolo> ruoli) {
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setPassword(bCrypt.encode(dto.password()));
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setRuoli(ruoli);
        return utenteRepository.save(utente);
    }

    public Utente findByEmail(String email){
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public Utente update(Long id, UtenteDTO dto) {
        Utente existing = findById(id);
        existing.setUsername(dto.username());
        existing.setEmail(dto.email());
        existing.setPassword(bCrypt.encode(dto.password()));
        existing.setNome(dto.nome());
        existing.setCognome(dto.cognome());

        if (dto.ruoliId() != null && !dto.ruoliId().isEmpty()) {
            List<Long> idsPuliti = dto.ruoliId().stream()
                    .filter(Objects::nonNull)
                    .toList();
            Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(idsPuliti));
            existing.setRuoli(ruoli);
        } else {
            existing.setRuoli(new HashSet<>());
        }

        return utenteRepository.save(existing);
    }

    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }

    public Utente updateDTO(Long id, UtenteUpdateDTO dto) {
        Utente existing = findById(id);

        if (dto.username() != null) {
            if (dto.username().isBlank()) {
                throw new BadRequestException("Lo username non può essere vuoto.");
            }
            existing.setUsername(dto.username());
        }

        if (dto.email() != null) {
            if (dto.email().isBlank()) {
                throw new BadRequestException("L'email non può essere vuota.");
            }
            if (!dto.email().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new BadRequestException("Formato email non valido.");
            }
            existing.setEmail(dto.email());
        }

        if (dto.password() != null) {
            if (dto.password().isBlank()) {
                throw new BadRequestException("La password non può essere vuota.");
            }
            existing.setPassword(bCrypt.encode(dto.password()));
        }

        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.cognome() != null) existing.setCognome(dto.cognome());

        if (dto.ruoliId() != null) {
            List<Long> idsPuliti = dto.ruoliId().stream()
                    .filter(Objects::nonNull)
                    .toList();
            Set<Ruolo> ruoli = new HashSet<>(ruoloRepository.findAllById(idsPuliti));
            existing.setRuoli(ruoli);
        }

        return utenteRepository.save(existing);
    }


    public boolean existsByUsername(String username) {
        return this.utenteRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email){return this.utenteRepository.existsByEmail(email);}
}
