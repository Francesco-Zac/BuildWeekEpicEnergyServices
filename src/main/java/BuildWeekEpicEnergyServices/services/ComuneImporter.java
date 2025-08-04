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
                        .withFirstRecordAsHeader()
                        .withTrim());
        ) {
            for (CSVRecord record : csvParser) {
                String nomeProvinciaOriginale = record.get(3).trim();

                final String nomeProvincia;

                if (nomeProvinciaOriginale.equalsIgnoreCase("Verbano-Cusio-Ossola")) {
                    nomeProvincia = "Verbania";
                } else if (nomeProvinciaOriginale.equalsIgnoreCase("Valle d'Aosta/Vallée d'Aoste")) {
                    nomeProvincia = "Aosta";
                }  else if (nomeProvinciaOriginale.equalsIgnoreCase("Monza e della Brianza")) {
                    nomeProvincia = "Monza-Brianza";
                }
                else if (nomeProvinciaOriginale.equalsIgnoreCase("Bolzano/Bozen")) {
                    nomeProvincia = "Bolzano";
                }
                else if (nomeProvinciaOriginale.equalsIgnoreCase("La Spezia")) {
                    nomeProvincia = "La-Spezia";
                }
                else if (nomeProvinciaOriginale.equalsIgnoreCase("Reggio nell'Emilia")) {
                    nomeProvincia = "Reggio-Emilia";
                }
                else if (nomeProvinciaOriginale.equalsIgnoreCase("Forlì-Cesena")) {
                    nomeProvincia = "Forli-Cesena";
                }else if (nomeProvinciaOriginale.equalsIgnoreCase("Pesaro e Urbino")) {
                    nomeProvincia = "Pesaro-Urbino";
                }else if (nomeProvinciaOriginale.equalsIgnoreCase("Ascoli Piceno")) {
                    nomeProvincia = "Ascoli-Piceno";
                }else if (nomeProvinciaOriginale.equalsIgnoreCase("Reggio Calabria")) {
                    nomeProvincia = "Reggio-Calabria";
                }else if (nomeProvinciaOriginale.equalsIgnoreCase("Vibo Valentia")) {
                    nomeProvincia = "Vibo-Valentia";
                }else if (nomeProvinciaOriginale.equalsIgnoreCase("Sud Sardegna")) {
                    nomeProvincia = "Vibo-Valentia";
                }
                else {
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