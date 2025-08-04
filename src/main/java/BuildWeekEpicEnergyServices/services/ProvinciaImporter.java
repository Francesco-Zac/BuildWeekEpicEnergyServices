package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Provincia;
import BuildWeekEpicEnergyServices.repositories.ProvinciaRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ProvinciaImporter {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaDaCsv(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withDelimiter(';')
                                .withFirstRecordAsHeader()
                                .withIgnoreSurroundingSpaces()
                )
        ) {
            for (CSVRecord record : csvParser) {
                Provincia provincia = new Provincia();
                provincia.setSigla(record.get("Sigla"));
                provincia.setProvincia(record.get("Provincia"));
                provincia.setRegione(record.get("Regione"));
                provinciaRepository.save(provincia);
            }
        }
    }
}
