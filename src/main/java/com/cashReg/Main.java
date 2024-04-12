package com.cashReg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Email:");

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }
}
