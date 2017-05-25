package edu.hm.authentification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;

/**
 * Token used within the authentification-service.
 */
public class Token {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final long EXPIRES_ON_OFFSET = 600_000; // 10 minutes

    private static final int NUMBITS = 130; // generate an int from 2^NUMBITS-1
    @JsonProperty("token")
    private final String token;
    private long expiresOn;

    public Token() {
        this("", 0);
    }

    private Token(String token, long expiresOn) {
        this.token = token;
        this.expiresOn = expiresOn;
    }

    /**
     * Generates a new token.
     *
     * @return a fresh token.
     */
    public static Token getToken() {
        String randToken = new BigInteger(NUMBITS, SECURE_RANDOM).toString(32);
        long expiresOn = Calendar.getInstance().getTimeInMillis() + EXPIRES_ON_OFFSET;
        return new Token(randToken, expiresOn);
    }

    /**
     * Resets the expiration date of this token (e.g. adds the offset)
     */
    public void resetTimer() {
        expiresOn = Calendar.getInstance().getTimeInMillis() + EXPIRES_ON_OFFSET;
    }

    /**
     * Checks wether this token has expired.
     *
     * @return true if expired, false if not
     */
    public boolean hasExpired() {
        return Calendar.getInstance().getTimeInMillis() > expiresOn;
    }

    @JsonIgnore
    public String getTokenString() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token t = (Token) o;

        return token.equals(t.token);
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + (int) (expiresOn ^ (expiresOn >>> 32));
        return result;
    }
}
