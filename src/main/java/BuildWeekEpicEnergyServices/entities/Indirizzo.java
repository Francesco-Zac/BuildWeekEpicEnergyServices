package BuildWeekEpicEnergyServices.entities;

import BuildWeekEpicEnergyServices.enums.TipoSede;
import jakarta.persistence.*;

@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private int civico;
    private int cap;
    @Enumerated(value = EnumType.STRING)
    private TipoSede tipoSede;
    
}
