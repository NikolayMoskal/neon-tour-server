/**
 * JwtTokenUtils.java
 */
package by.neon.tour.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import by.neon.tour.model.JwtUser;
import by.neon.tour.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Nikolay Moskal
 */
@Component
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = -2321982185026241010L;
    @Autowired
    private UserService userService;

    private static final String CLAIM_KEY_ROLE = "role";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    /**
     * Extracts all JWT properties
     *
     * @param token the JWT token
     * @return Claims
     */
    public Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Extracts the authenticated user name from JWT
     *
     * @param token the JWT token
     * @return String
     */
    public String getUsername(String token) {
        String username;
        try {
            final Claims claims = getClaims(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Extracts the created date from JWT
     *
     * @param token the JWT token
     * @return Date
     */
    public Date getCreatedDate(String token) {
        Date date;
        try {
            final Claims claims = getClaims(token);
            date = new Date((long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * Extracts the expiration date from JWT
     *
     * @param token the JWT token
     * @return Date
     */
    public Date getExpirationDate(String token) {
        Date date;
        try {
            final Claims claims = getClaims(token);
            date = claims.getExpiration();
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * Generates the expiration date for JWT
     *
     * @return Date
     */
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    /**
     * Creates the new JWT token using authenticated user
     *
     * @param user the authenticated user
     * @return String
     */
    public String generateToken(JwtUser user) {
        Claims claims = Jwts.claims();
        claims.setSubject(user.getUsername());
        claims.put(CLAIM_KEY_ROLE, user.getRole());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * Creates the new JWT token using user name
     *
     * @param username the name of authenticated user
     * @return String
     */
    public String generateToken(String username) {
        Claims claims = Jwts.claims();
        claims.setSubject(username);
        claims.put(CLAIM_KEY_ROLE, userService.findByName(username).getRole());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * Compact the JWT token
     *
     * @param claims the map of properties for JWT
     * @return String
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * Inserts the <b>Authorization</b> header including JWT token into a
     * response. Also this method inserts into a response the list of user roles
     * in this web application
     *
     * @param response the response from server
     * @param username the name of authenticated user
     */
    public void addAuthentication(HttpServletResponse response, String username) {
        String token = generateToken(username);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"token\":").append("\"").append(token).append("\"");
        builder.append(", ");
        builder.append("\"role\":");
        builder.append("[");
        List<String> roles = new ArrayList<>();
        for (String str : userService.findByName(username).getRole().split(",")) {
            roles.add("\"" + str + "\"");
        }
        builder.append(String.join(",", roles));
        builder.append("]");
        builder.append("}");

        try {
            PrintWriter writer = response.getWriter();
            writer.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the authentication token with given user and his authorities
     *
     * @param request the request from client
     * @return Authentication
     */
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            Claims claims = getClaims(token);
            if (claims.getSubject() != null) {
                JwtUser user = new JwtUser(claims.getSubject(), (String) claims.get(CLAIM_KEY_ROLE));
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
                return new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);
            }
            return null;
        }
        return null;
    }
}
