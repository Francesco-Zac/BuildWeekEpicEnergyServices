package BuildWeekEpicEnergyServices.entities;

import BuildWeekEpicEnergyServices.enums.TipoSede;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
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
    @JsonIgnore
    private Cliente cliente;

    public Indirizzo(String via, int civico, int cap, TipoSede tipoSede, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.tipoSede = tipoSede;
        this.comune = comune;

    }
}
