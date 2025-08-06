package BuildWeekEpicEnergyServices.services;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.exceptions.UnauthorisedException;
import BuildWeekEpicEnergyServices.payloads.LoginDTO;
import BuildWeekEpicEnergyServices.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtentiServices utentiServices;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bCrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.utentiServices.findByEmail(body.email());

        if(bCrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorisedException("Wrong credentials!");
        }
    }

}