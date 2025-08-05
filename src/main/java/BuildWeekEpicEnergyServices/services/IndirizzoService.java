package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.IndirizzoDTO;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneRepository comuneRepository;


    public Page<Indirizzo> findAll(Pageable pageable) {
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(Long id) {
        return indirizzoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Indirizzo con ID " + id + " non trovato"));
    }

    public Indirizzo save(IndirizzoDTO dto) {

        Comune comune = comuneRepository.findById(dto.comuneId())
                .orElseThrow(() -> new NotFoundException("Comune con ID " + dto.comuneId() + " non trovato"));


        Indirizzo newIndirizzo = new Indirizzo();
        newIndirizzo.setVia(dto.via());
        newIndirizzo.setCivico(dto.civico());
        newIndirizzo.setCap(dto.cap());
        newIndirizzo.setTipoSede(dto.tipoSede());
        newIndirizzo.setComune(comune);

        Indirizzo saved = indirizzoRepository.save(newIndirizzo);

        return saved;
    }

    public Indirizzo update(Long id, IndirizzoDTO payload) {
        Comune comune = comuneRepository.findById(payload.comuneId())
                .orElseThrow(() -> new NotFoundException("Comune con ID " + payload.comuneId() + " non trovato"));

        Indirizzo found = this.findById(id);
        found.setVia(payload.via());
        found.setCivico(payload.civico());
        found.setCap(payload.cap());
        found.setTipoSede(payload.tipoSede());
        found.setComune(comune);
        return indirizzoRepository.save(found);
    }

    public void delete(Long id) {
        Indirizzo existing = findById(id);
        indirizzoRepository.delete(existing);
    }
}
