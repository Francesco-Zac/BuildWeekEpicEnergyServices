package BuildWeekEpicEnergyServices.runners;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.exceptions.ValidationException;
import BuildWeekEpicEnergyServices.payloads.NuovoUtenteRespDTO;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Component
public class AdminRunner implements CommandLineRunner {

    @Autowired
    private UtentiServices utentiServices;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public void run(String... args) {

        String username = "admin";

        if (utentiServices.existsByUsername(username)) {
            System.out.println("Utente admin già presente, non viene ricreato.");
            return;
        }

        Ruolo ruoloAdmin = ruoloRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Ruolo ADMIN non trovato"));

        UtenteDTO adminTest = new UtenteDTO(username, "test@admin.com", "1234", "Admin", "Test", Set.of(2L));
        utentiServices.create(adminTest, Set.of(ruoloAdmin));

        System.out.println("Utente admin creato con successo.");
    }
}



