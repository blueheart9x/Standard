package com.elcom.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTutils {
	
    private final static String SECRET_KEY = "elcom_wq3Dr8O5wrkCSybDkQ==1_2020@)@)";

    public static String createToken(String content) {
        try {
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("content", content)
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception ex) {
        	System.out.println(ex.toString());
        }
        return null;
    }

    public static String getContentInToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("content").asString();
        } catch (Exception ex) {
        	System.out.println(ex.toString());
        }
        return null;
    }
    
    public static void main(String[] args) {
		
    	System.out.println( createToken("anhdv@elcom.com.vn") );
    	
    	System.out.println( getContentInToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImNvbnRlbnQiOiJhbmhkdkBlbGNvbS5jb20udm4ifQ.l2cz86uK0zugYmTjwbIPry1LK4WzZP6ypazIb3JoCUM") );
    	// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImNvbnRlbnQiOiJhbmhkdkBlbGNvbS5jb20udm4ifQ.YRJUCqtiZj8yaIENa8fsEwYlmf7uASK5eCE7XBZIRWk
	}
}