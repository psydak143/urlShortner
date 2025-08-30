package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

// For n>=62, collisions will start to happen due to the base 62 encoding
public class MinimalShortener {

    private static final Map<String, String> codeToUrl = new HashMap<>();
    private static final Map<String, String> urlToCode = new HashMap<>();

    // Base 62 encoding with unique counter
    private static final AtomicLong counter = new AtomicLong(1);
    private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public String shorten(String longUrl) {
        if (longUrl == null || longUrl.isBlank()) throw new IllegalArgumentException("URL is blank");
        // Reuse if we've seen it
        String existing = urlToCode.get(longUrl);
        if (existing != null) return existing;
        // Create new code
        long id = counter.getAndIncrement();
        String code = toBase62(id);
        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);
        return code;
    }

    public String resolve(String code) {
        if (code == null) return null;
        return codeToUrl.get(code);
    }

    private String toBase62(long n) {
        if (n == 0) return String.valueOf(ALPHABET[0]);
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int idx = (int) (n % 62);
            sb.append(ALPHABET[idx]);
            n /= 62;
        }
        return sb.reverse().toString();
    }
}