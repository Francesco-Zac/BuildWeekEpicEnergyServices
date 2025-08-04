package BuildWeekEpicEnergyServices.runners;

import BuildWeekEpicEnergyServices.services.ComuneImporter;
import BuildWeekEpicEnergyServices.services.ProvinciaImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvRunner implements CommandLineRunner {

    @Autowired
    private ComuneImporter comuneImporter;

    @Autowired
    private ProvinciaImporter provinciaImporter;

    @Override
    public void run(String... args) throws Exception {
        comuneImporter.importaDaCsv("D:/Epicode/comuni/comuni-italiani.csv");
        provinciaImporter.importaDaCsv("D:/Epicode/province/province-italiane.csv");
    }
}
