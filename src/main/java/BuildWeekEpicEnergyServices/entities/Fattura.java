package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "numero_fattura")
    private long numeroFattura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "stato_id", nullable = false)
    private StatoFattura statoFattura;

    @Column(nullable = false)
    private LocalDate data;


    @Column(nullable = false)
    private BigDecimal importo;
}