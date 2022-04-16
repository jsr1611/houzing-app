package uz.digitalone.houzingapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtEncoder jwtEncoder;

    @Value("${jwt.expiration.time}")
    Long EXPIRATION_TIME;


    public String generateToken(User user){
        return generateTokenWithUsername(user.getUsername());
    }

    public String generateTokenWithUsername(String username) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.
                builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(getJwtExpirationData()))
                .subject(username)
                .claim("scope", "ROLE_USER")
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
    public Long getJwtExpirationData(){
        return EXPIRATION_TIME;
    }
}
