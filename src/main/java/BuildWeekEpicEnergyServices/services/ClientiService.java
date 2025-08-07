package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Cliente;
import BuildWeekEpicEnergyServices.entities.Indirizzo;
import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.enums.TipoSede;
import BuildWeekEpicEnergyServices.exceptions.BadRequestException;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.payloads.ClienteUpdateDTO;
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

    public Cliente findByIdAndUpdate(long id, ClienteUpdateDTO dto) {
        Cliente existing = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + id + " non trovato"));

        if (dto.ragioneSociale() != null) {
            if (dto.ragioneSociale().isBlank()) {
                throw new BadRequestException("La ragione sociale non può essere vuota.");
            }
            existing.setRagioneSociale(dto.ragioneSociale());
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

        if (dto.pec() != null) {
            existing.setPec(dto.pec());
        }

        if (dto.numeroTelefono() != null) {
            existing.setNumeroTelefono(dto.numeroTelefono());
        }

        if (dto.emailContatto() != null) {
            existing.setEmailContatto(dto.emailContatto());
        }

        if (dto.nomeContatto() != null) {
            existing.setNomeContatto(dto.nomeContatto());
        }

        if (dto.cognomeContatto() != null) {
            existing.setCognomeContatto(dto.cognomeContatto());
        }

        if (dto.telefonoContatto() != null) {
            existing.setTelefonoContatto(dto.telefonoContatto());
        }

        if (dto.ultimoContattoIl() != null) {
            existing.setUltimoContattoIl(dto.ultimoContattoIl());
        }

        if (dto.fatturatoAnnuo() != null) {
            existing.setFatturatoAnnuo(dto.fatturatoAnnuo());
        }

        if (dto.tipo() != null) {
            existing.setTipo(dto.tipo());
        }

        return clienteRepository.save(existing);
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

    // FILTRI E ORDINAMENTI

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

    public Page<Cliente> getClientiByNomeContatto(String nomeContatto, int page, int size) {
        return clienteRepository.findByNomeContattoContainingIgnoreCase(nomeContatto, PageRequest.of(page, size));
    }

    public Page<Cliente> getClientiByMinFatturato(double minFatturato, int page, int size) {
        return clienteRepository.findByFatturatoAnnuoGreaterThanEqual(minFatturato, PageRequest.of(page, size));
    }

    public Page<Cliente> getClientiInseritiDopo(LocalDate data, int page, int size) {
        return clienteRepository.findByInseritoIlAfter(data, PageRequest.of(page, size));
    }

    public Page<Cliente> getClientiContattatiPrimaDi(LocalDate data, int page, int size) {
        return clienteRepository.findByUltimoContattoIlBefore(data, PageRequest.of(page, size));
    }

}
