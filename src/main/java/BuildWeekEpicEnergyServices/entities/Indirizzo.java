package BuildWeekEpicEnergyServices.entities;

import BuildWeekEpicEnergyServices.enums.TipoSede;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String via;
    private int civico;
    private int cap;
    @Enumerated(value = EnumType.STRING)
    private TipoSede tipoSede;
    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
