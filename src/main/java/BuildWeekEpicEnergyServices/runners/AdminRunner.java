package BuildWeekEpicEnergyServices.runners;

import BuildWeekEpicEnergyServices.entities.Ruolo;
import BuildWeekEpicEnergyServices.payloads.UtenteDTO;
import BuildWeekEpicEnergyServices.repositories.RuoloRepository;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        Ruolo ruoloAdmin = ruoloRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    System.out.println("Ruolo ADMIN non trovato, lo creo...");
                    return ruoloRepository.save(new Ruolo("ADMIN"));
                });

        Ruolo ruoloUser = ruoloRepository.findByName("USER")
                .orElseGet(() -> {
                    System.out.println("Ruolo USER non trovato, lo creo...");
                    return ruoloRepository.save(new Ruolo("USER"));
                });

        System.out.println("Ruolo USER creato con ID: " + ruoloUser.getId());

        if (utentiServices.existsByUsername(username)) {
            System.out.println("Utente admin già presente, non viene ricreato.");
            return;
        }

        UtenteDTO adminTest = new UtenteDTO(
                username,
                "test@admin.com",
                "1234",
                "Admin",
                "Test",
                Set.of(ruoloAdmin.getId())
        );

        utentiServices.create(adminTest, Set.of(ruoloAdmin));
        System.out.println("Utente admin creato con successo.");
    }
}



