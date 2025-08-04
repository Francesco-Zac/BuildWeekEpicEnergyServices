package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;



@Service
public class ComuneImporter {

    private final ComuneRepository comuneRepository;

    public ComuneImporter(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    public void importaDaCsv(String percorsoCsv) {
        try (
                Reader reader = new FileReader(percorsoCsv);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withFirstRecordAsHeader()
                        .withTrim());
        ) {
            for (CSVRecord record : csvParser) {
                Comune comune = new Comune();
                comune.setCodiceProvincia(record.get(0));
                comune.setProgressivoComune(record.get(1));
                comune.setDenominazioneComune(record.get(2));
                comune.setProvincia(record.get(3));

                comuneRepository.save(comune);
            }

            System.out.println("Importazione completata.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
