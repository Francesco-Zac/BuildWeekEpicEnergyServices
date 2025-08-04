package BuildWeekEpicEnergyServices.entities;

import BuildWeekEpicEnergyServices.enums.TipoAzienda;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;

    @Column(name = "partita_iva", nullable = false)
    private long partitaIva;

    private String email;

    @Column(name = "inserito_il")
    private LocalDate inseritoIl;

    @Column(name = "ultimo_contatto_il")
    private LocalDate ultimoContattoIl;

    @Column(name = "fatturato_annuo")
    private double fatturatoAnnuo;

    private String pec;

    @Column(name = "numero_telefono")
    private String numeroTelefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private String telefonoContatto;

    @Column(name = "logo_azienda")
    private String logoAzienda;

    @Enumerated(EnumType.STRING)
    private TipoAzienda tipo;

    @OneToOne
    @JoinColumn(name = "sede_operativa")
    private Indirizzo sedeOperativa;

    @OneToOne
    @JoinColumn(name = "sede_legale")
    private Indirizzo sedeLegale;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Fattura> fatture = new ArrayList<>();

    public Cliente(String ragioneSociale, long partitaIva, String email, LocalDate inseritoIl, LocalDate ultimoContattoIl, double fatturatoAnnuo, String pec, String numeroTelefono, String emailContatto, String nomeContatto, String cognomeContatto, String telefonoContatto, String logoAzienda, TipoAzienda tipo, Indirizzo sedeOperativa, Indirizzo sedeLegale) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.inseritoIl = inseritoIl;
        this.ultimoContattoIl = ultimoContattoIl;
        this.fatturatoAnnuo = fatturatoAnnuo;
        this.pec = pec;
        this.numeroTelefono = numeroTelefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAzienda = logoAzienda;
        this.tipo = tipo;
        this.sedeOperativa = sedeOperativa;
        this.sedeLegale = sedeLegale;
    }
}

