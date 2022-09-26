package com.bridgelabz.bookstoreapplication.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {
    private static final String TOKEN_SECRET = "BookStoreApplicationSecretKey";

    public String createToken(int id) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.create().withClaim("user_id", id).sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
//log Token Signing Failed
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int decodeToken(String token) {
        int id;
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        assert verification != null;
        JWTVerifier jwtverifier = verification.build();
        DecodedJWT decodedjwt = jwtverifier.verify(token);

        Claim claim = decodedjwt.getClaim("user_id");
        id = claim.asInt();
        return id;
    }
}


