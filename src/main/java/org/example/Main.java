package org.example;

import org.example.MinimalShortener;

public class Main {
    public static void main(String[] args) {

        MinimalShortener urlShortner = new MinimalShortener();
        String long1 = "https://example.com/products?ref=catalog&page=2";
        String long2 = "https://openjdk.org/";

        String code1 = urlShortner.shorten(long1);
        String code2 = urlShortner.shorten(long2);
        String code1Again = urlShortner.shorten(long1); // same code as code1

        System.out.println("Long 1: " + long1);
        System.out.println("Code 1: " + code1);
        System.out.println("Resolve: " + urlShortner.resolve(code1));
        System.out.println();
        System.out.println("Long 2: " + long2);
        System.out.println("Code 2: " + code2);
        System.out.println("Resolve: " + urlShortner.resolve(code2));
        System.out.println();
        System.out.println("Re-shorten Long 1 gives same code: " + code1Again);
    }
}