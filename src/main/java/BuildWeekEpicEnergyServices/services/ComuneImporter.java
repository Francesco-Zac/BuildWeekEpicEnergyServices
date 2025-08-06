package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Comune;
import BuildWeekEpicEnergyServices.entities.Provincia;
import BuildWeekEpicEnergyServices.exceptions.NotFoundException;
import BuildWeekEpicEnergyServices.repositories.ComuneRepository;
import BuildWeekEpicEnergyServices.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;



@Service
public class ComuneImporter {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaDaCsv(String percorsoCsv) {
        try (
                Reader reader = new FileReader(percorsoCsv);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withTrim()
                        .withSkipHeaderRecord()
                );
        ) {
            boolean isFirstRow = true;
            for (CSVRecord record : csvParser) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                String nomeProvinciaOriginale = record.get(3).trim();

                final String nomeProvincia;

                if (nomeProvinciaOriginale.equalsIgnoreCase("Verbania")) {
                    nomeProvincia = "Verbano-Cusio-Ossola";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Aosta")) {
                    nomeProvincia = "Valle d'Aosta/Vallée d'Aoste";
                }  else if (nomeProvinciaOriginale.equalsIgnoreCase("Monza-Brianza")) {
                    nomeProvincia = "Monza e della Brianza";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Bolzano")) {
                    nomeProvincia = "Bolzano/Bozen";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("La-Spezia")) {
                    nomeProvincia = "La Spezia";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Reggio Emilia")) {
                    nomeProvincia = "Reggio nell'Emilia";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Forlì-Cesena")) {
                    nomeProvincia = "Forli-Cesena";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Pesaro-Urbino")) {
                    nomeProvincia = "Pesaro e Urbino";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Ascoli-Piceno")) {
                    nomeProvincia = "Ascoli Piceno";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Reggio-Calabria")) {
                    nomeProvincia = "Reggio Calabria";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Vibo-Valentia")) {
                    nomeProvincia = "Vibo Valentia";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Sud Sardegna")) {
                    nomeProvincia = "Carbonia Iglesias";
                } else {
                    nomeProvincia = nomeProvinciaOriginale;
                }

                Provincia provincia = provinciaRepository.findByProvincia(nomeProvincia)
                        .orElseThrow(() -> new NotFoundException("Provincia non trovata: " + nomeProvincia));

                Comune comune = new Comune();
                comune.setCodiceProvincia(record.get(0));
                comune.setProgressivoComune(record.get(1));
                comune.setDenominazioneComune(record.get(2));
                comune.setProvincia(provincia);

                comuneRepository.save(comune);
            }

            System.out.println("Importazione completata.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}