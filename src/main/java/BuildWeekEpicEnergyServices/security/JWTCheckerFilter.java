package BuildWeekEpicEnergyServices.security;

import BuildWeekEpicEnergyServices.entities.Utente;
import BuildWeekEpicEnergyServices.exceptions.UnauthorisedException;
import BuildWeekEpicEnergyServices.services.UtentiServices;
import BuildWeekEpicEnergyServices.tools.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    @Autowired
    private UtentiServices utentiService;

    @Autowired
    private JWTTools jwtTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorisedException("Insert right format token");

        String accessToken = authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(accessToken);
        String utenteId = jwtTools.extractIdFromToken(accessToken);
        Utente currentUtente = this.utentiService.findById(Long.parseLong(utenteId));

        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null, currentUtente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match("/province-comuni/**", path);
    }
}
