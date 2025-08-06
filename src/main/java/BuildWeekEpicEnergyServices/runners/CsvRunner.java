package BuildWeekEpicEnergyServices.runners;

import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.ProvinciaRepository;
import BuildWeekEpicEnergyServices.services.ComuneImporter;
import BuildWeekEpicEnergyServices.services.ProvinciaImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvRunner implements CommandLineRunner {

    @Autowired
    private ComuneImporter comuneImporter;
    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaImporter provinciaImporter;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Value("${path.comuni}")
    private String pathComuni;
    @Value("${path.province}")
    private String pathProvince;
    @Override
    public void run(String... args) throws Exception {
        if (provinciaRepository.count() == 0) {
            provinciaImporter.importaDaCsv(pathProvince);
            System.out.println("Importazione province completata.");
        } else {
            System.out.println("Province già presenti nel database.");
        }
        if (comuneRepository.count() == 0) {
            comuneImporter.importaDaCsv(pathComuni);
            System.out.println("Importazione comuni completata.");
        } else {
            System.out.println("Comuni già presenti nel database.");
        }

    }
}

