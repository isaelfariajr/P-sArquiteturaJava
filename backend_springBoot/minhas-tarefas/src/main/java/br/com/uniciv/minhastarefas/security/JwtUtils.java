package br.com.uniciv.minhastarefas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

//Classe para geração e validação do token jwt
@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${app.jwt.SecretKey}")
    private String jwtSecret;

    @Value("${app.jwt.ExpirationMs}")
    private Integer jwtExpirationMs;

    //Metodo para gerar um tokenValido
    public String generateJwtToken(Authentication authentication) {

        //Cria info de usuario
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        //Pega a data atual
        Date currentTime = new Date();
        //Cria a data de expiração
        Date expirationTime = new Date(currentTime.getTime() + jwtExpirationMs);

        //cria o token
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //Extraio do token o usuario
    public String getUserNameFromJwtToken(String token) {

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Valida se o token esta valido
    public boolean validateJwsToken(String authToken) {

        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);

            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
