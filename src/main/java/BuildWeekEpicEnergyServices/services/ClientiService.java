package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.enums.TipoSede;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.NuovoClienteDTO;
import BuildWeekEpicEnergyServices.payloads.IndirizzoDTO;
import BuildWeekEpicEnergyServices.repositories.ClienteRepository;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClientiService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    private Indirizzo costruisciIndirizzo(IndirizzoDTO dto, TipoSede tipo, Cliente cliente) {
        Comune comune = comuneRepository.findById(dto.comuneId())
                .orElseThrow(() -> new NotFoundException("Comune non trovato con id: " + dto.comuneId()));

        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(dto.via());
        indirizzo.setCap(dto.cap());
        indirizzo.setCivico(dto.civico());
        indirizzo.setTipoSede(tipo);
        indirizzo.setComune(comune);
        indirizzo.setCliente(cliente);

        return indirizzo;
    }

    public Cliente creaCliente(NuovoClienteDTO dto) {
        Cliente cliente = new Cliente();

        try {
            cliente.setRagioneSociale(dto.ragioneSociale());
            cliente.setPartitaIva(dto.partitaIva());
            cliente.setEmail(dto.email());
            cliente.setUltimoContattoIl(dto.ultimoContattoIl());
            cliente.setFatturatoAnnuo(dto.fatturatoAnnuo());
            cliente.setPec(dto.pec());
            cliente.setNumeroTelefono(dto.numeroTelefono());
            cliente.setEmailContatto(dto.emailContatto());
            cliente.setNomeContatto(dto.nomeContatto());
            cliente.setCognomeContatto(dto.cognomeContatto());
            cliente.setTelefonoContatto(dto.telefonoContatto());
            cliente.setLogoAzienda(dto.logoAzienda());
            cliente.setTipo(dto.tipoAzienda());
            cliente.setInseritoIl(LocalDate.now());

            cliente = clienteRepository.save(cliente);

            Indirizzo sedeLegale = costruisciIndirizzo(dto.sedeLegale(), TipoSede.LEGALE, cliente);
            Indirizzo sedeOperativa = costruisciIndirizzo(dto.sedeOperativa(), TipoSede.OPERATIVA, cliente);

            indirizzoRepository.save(sedeLegale);
            indirizzoRepository.save(sedeOperativa);

            cliente.setSedeLegale(sedeLegale);
            cliente.setSedeOperativa(sedeOperativa);

            cliente.getIndirizzi().add(sedeLegale);
            cliente.getIndirizzi().add(sedeOperativa);

            return clienteRepository.save(cliente);

        } catch (Exception ex) {
            if (cliente.getId() != 0 && clienteRepository.existsById(cliente.getId())) {
                indirizzoRepository.deleteAllByClienteId(cliente.getId());
                clienteRepository.deleteById(cliente.getId());
            }
            throw new RuntimeException("Errore durante la creazione del cliente: " + ex.getMessage());
        }
    }


}
