package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "stato_id", nullable = false)
    private StatoFattura statoFattura;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private BigDecimal importo;
}