package ma.tresorito.gestionContact.jwtSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "38782F413F4428472B4B6250655368566D597133743676397924422645294840" ;


    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(generateSignInKey())
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = getClaims(token);
         return claimResolver.apply(claims);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key generateSignInKey() {
        byte[] secretByteKey = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(secretByteKey);

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername())
                && !isExpirationToken(token);
    }

    private boolean isExpirationToken(String token) {
        return getClaim(token, Claims::getExpiration).before(Date.valueOf(LocalDate.now()));
    }
}
