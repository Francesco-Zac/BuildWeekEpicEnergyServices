package BuildWeekEpicEnergyServices.entities;

import BuildWeekEpicEnergyServices.enums.CompanyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "vat_number", nullable = false)
    private long vatNumber;

    private String email;

    @Column(name = "inserted_on")
    private LocalDate insertedOn;

    @Column(name = "last_contacted_on")
    private LocalDate lastContactedOn;

    @Column(name = "annual_turnover")
    private double annualTurnover;

    private String pec;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_surname")
    private String contactSurname;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "company_logo")
    private String companyLogo;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    public Customer(String companyName, long vatNumber, String email, LocalDate insertedOn, LocalDate lastContactedOn, double annualTurnover, String pec, String phoneNumber, String contactEmail, String contactName, String contactSurname, String contactPhoneNumber, String companyLogo, CompanyType type) {
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.insertedOn = insertedOn;
        this.lastContactedOn = lastContactedOn;
        this.annualTurnover = annualTurnover;
        this.pec = pec;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhoneNumber = contactPhoneNumber;
        this.companyLogo = companyLogo;
        this.type = type;
    }
}
