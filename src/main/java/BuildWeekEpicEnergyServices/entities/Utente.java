package BuildWeekEpicEnergyServices.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "utenti")
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_ruolo")
    )
    private Set<Ruolo> ruolo = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruolo.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .toList();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Set<Ruolo> getRuoli() {
        return ruolo;
    }
    public void setRuoli(Set<Ruolo> ruolo) {
        this.ruolo = ruolo;
    }
}