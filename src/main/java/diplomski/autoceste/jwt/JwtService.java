package diplomski.autoceste.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    JwtConfig config;

    @Autowired
    public JwtService(JwtConfig config) {
        this.config = config;
    }

    public String getUserEmail(String token){


        Jws<Claims> claimsJwts = Jwts.parserBuilder()
                .setSigningKey(config.getSecretKey())
                .build()
                .parseClaimsJws(token.replace(config.getTokenPrefix(), ""));

        Claims body = claimsJwts.getBody();
        return body.getSubject();
    }

}
