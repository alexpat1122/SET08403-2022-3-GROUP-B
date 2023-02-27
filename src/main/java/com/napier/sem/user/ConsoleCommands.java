package com.napier.sem.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleCommands {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static void prompts() {
        System.out.println("Select a query option: ");
        System.out.println("1: All the countries in ....");
        System.out.println("2: The top N populated countries ....");
        System.out.println("3: All the cities ....");
        System.out.println("4: The top N populated cities ....");
        System.out.println("5: All the capital cities ....");
        System.out.println("6: The top N populated capital cities in....");
        System.out.println("6: The population of people, people living in cities, and people not living in cities in each....");
    }

    static void secondTierOptions(int index) {
        switch (index) {
            case 1: {
                allCountries();
                break;
            }
            default: System.out.println("Incorrect option selected at 2nd tier of options");
        }
    }


   private static void allCountries() {
        System.out.println("Select a query option: ");
        System.out.println("1: All the countries in the world organised by largest population to smallest");
        System.out.println("2: All the countries in a continent organised by largest population to smallest");
    }

    public static void allContinents() {
        System.out.println("Select a query option: ");
        System.out.println("1: Asia");
        System.out.println("2: Europe");
        System.out.println("3: North America");
        System.out.println("4: Africa");
        System.out.println("5: South America");
        System.out.println("6: Oceania");
        System.out.println("7: Antarctica");
    }
}
