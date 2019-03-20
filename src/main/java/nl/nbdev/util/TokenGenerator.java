package nl.nbdev.util;

import java.util.UUID;

public class TokenGenerator {

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
