package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stati_fattura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatoFattura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @OneToMany(mappedBy = "statoFattura")
    private List<Fattura> fatture;
}
