package BuildWeekEpicEnergyServices.runners;

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
    private ProvinciaImporter provinciaImporter;

    @Value("${path.comuni}")
    private String pathComuni;
    @Value("${path.province}")
    private String pathProvince;
    @Override
    public void run(String... args) throws Exception {
        comuneImporter.importaDaCsv(pathComuni);
        provinciaImporter.importaDaCsv(pathProvince);
    }
}
