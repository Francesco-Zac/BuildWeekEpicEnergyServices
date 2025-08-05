package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;


    public Page<Indirizzo> findAll(Pageable pageable) {
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(Long id) {
        return indirizzoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Indirizzo con ID " + id + " non trovato"));
    }

    public Indirizzo save(Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    public Indirizzo update(Long id, Indirizzo indirizzo) {
        Indirizzo existing = findById(id);
        existing.setVia(indirizzo.getVia());
        existing.setCivico(indirizzo.getCivico());
        existing.setCap(indirizzo.getCap());
        existing.setTipoSede(indirizzo.getTipoSede());
        existing.setComune(indirizzo.getComune());
        return indirizzoRepository.save(existing);
    }

    public void delete(Long id) {
        Indirizzo existing = findById(id);
        indirizzoRepository.delete(existing);
    }
}
