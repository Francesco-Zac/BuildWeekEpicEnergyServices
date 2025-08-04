package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comuni")
@Getter
@Setter
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_provincia")
    private String codiceProvincia;

    @Column(name = "progressivo_comune")
    private String progressivoComune;

    @Column(name = "denominazione_comune")
    private String denominazioneComune;

    @ManyToOne
    @JoinColumn(name = "sigla_provincia", referencedColumnName = "sigla")
    private Provincia provincia;


}
