package com.sevak.security.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    //@Value("${jwt.secret}")
    private String secretKey="sevak";

    //@Value("${jwt.header}" )
    private String authorizationHeader ="Authorization" ;
   // @Value("{jwt.expiration}")
    private Long validityInMilliseconds=600L;
//constructorov e
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init(){

        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //stextsum enq token
    public String createToken(String username, String role){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date() ;
        Date validity = new Date(now.getTime()+validityInMilliseconds*1000);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //stugum enq token@ valida te che
    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthenticationException(("Jwt token is expired or invalid"), HttpStatus.UNAUTHORIZED);
        }
    }

    //Tokenic vercnum enq Authintification@

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //tokenic vercnum enq username
    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //harcumic token vercnel@
    //resolve tuylatrel
    public String resolveToken(HttpServletRequest request){
        return request.getHeader(authorizationHeader);
    }
}
