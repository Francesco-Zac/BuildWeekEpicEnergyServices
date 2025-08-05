package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.enums.TipoSede;
import BuildWeekEpicEnergyServices.exceptions.BadRequestException;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.NuovoClienteDTO;
import BuildWeekEpicEnergyServices.payloads.IndirizzoDTO;
import BuildWeekEpicEnergyServices.repositories.ClienteRepository;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.IndirizzoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClientiService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private Cloudinary imgUploader;

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
            cliente.setTipo(dto.tipoAzienda());
            cliente.setInseritoIl(LocalDate.now());
            cliente.setLogoAzienda("https://ui-avatars.com/api/?name=" + dto.ragioneSociale());

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

    public Cliente findById(long clienteId) {
        return this.clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Cliente findByIdAndUpdate(long id, NuovoClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + id + " non trovato"));

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

            indirizzoRepository.deleteAllByClienteId(cliente.getId());

            Indirizzo nuovaSedeLegale = costruisciIndirizzo(dto.sedeLegale(), TipoSede.LEGALE, cliente);
            Indirizzo nuovaSedeOperativa = costruisciIndirizzo(dto.sedeOperativa(), TipoSede.OPERATIVA, cliente);
            indirizzoRepository.save(nuovaSedeLegale);
            indirizzoRepository.save(nuovaSedeOperativa);

            cliente.setSedeLegale(nuovaSedeLegale);
            cliente.setSedeOperativa(nuovaSedeOperativa);

            cliente.getIndirizzi().clear();
            cliente.getIndirizzi().add(nuovaSedeLegale);
            cliente.getIndirizzi().add(nuovaSedeOperativa);

            return clienteRepository.save(cliente);

        } catch (Exception ex) {
            throw new RuntimeException("Errore durante l'aggiornamento del cliente: " + ex.getMessage());
        }
    }

    public void findByIdAndDelete(long clienteId) {
        Cliente found = this.findById(clienteId);
        this.clienteRepository.delete(found);
    }


    public String uploadLogo(long clienteId, MultipartFile file) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + clienteId));

        try {
            String imageUrl = (String) imgUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            cliente.setLogoAzienda(imageUrl);
            clienteRepository.save(cliente);

            return imageUrl;

        } catch (IOException e) {
            throw new BadRequestException("C'è stato un problema! L'immagine non è stata caricata");
        }
    }

    public Page<Cliente> getAllClienti(int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size);

        if (sortBy.equalsIgnoreCase("provinciaSedeLegale")) {
            return direction.equalsIgnoreCase("desc") ?
                    clienteRepository.findAllOrderByProvinciaSedeLegaleDesc(pageable) :
                    clienteRepository.findAllOrderByProvinciaSedeLegale(pageable);
        }

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        pageable = PageRequest.of(page, size, sort);
        return clienteRepository.findAll(pageable);
    }


    public Page<Cliente> getClientiOrdinatiPerProvinciaLegaleAsc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAllOrderByProvinciaSedeLegale(pageable);
    }

    public Page<Cliente> getClientiOrdinatiPerProvinciaLegaleDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clienteRepository.findAllOrderByProvinciaSedeLegaleDesc(pageable);
    }

}
