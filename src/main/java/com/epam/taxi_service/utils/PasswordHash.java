package com.epam.taxi_service.utils;


import com.epam.taxi_service.Exception.IncorrectPasswordException;
import de.mkammerer.argon2.*;

public class PasswordHash {
    private static final Argon2 argon2 = Argon2Factory.create();
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 15*1024;
    private static final int PARALLELISM = 1;


    public static String encode(String password) {
        return password != null ? argon2.hash(ITERATIONS,MEMORY,PARALLELISM, password.toCharArray()) : "";
    }


    public static void verify(String hash, String password) throws IncorrectPasswordException {
        if (!argon2.verify(hash, password.toCharArray())) {
            throw new IncorrectPasswordException();
        }
    }


}