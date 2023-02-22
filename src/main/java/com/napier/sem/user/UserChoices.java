package com.napier.sem.user;

import com.napier.sem.constant.Constants;
import com.napier.sem.database.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserChoices {


    public static void chooseAReport(java.sql.Connection con) throws IOException {
        ConsoleCommands.prompts();
        int option = (selectOption(1));
        switch (option) {
            case 1: {
                allCountriesRequests(option,con);
                break;
            }
            default: System.out.println("Non valid choice was made");
        }
    }

    private static int selectOption (int upperLimit){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please select an option");
        while (true) {
            try {
                int number = Integer.parseInt(reader.readLine());
                if(number < 1 || number > upperLimit) {
                    System.out.println("Please select from one of the options provided, 1-" + upperLimit);
                }
                else {
                    return number;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

        private static void allCountriesRequests(int option, java.sql.Connection con) throws IOException {
            ConsoleCommands.secondTierOptions(option);
            option = selectOption(2);
            switch (option) {
                case 1 : {
                    Request.selectRequest(Constants.ALL_COUNTRIES_DESC, con);
                    break;
                }
                case 2 : {
                    ConsoleCommands.allContinents();
                    option = selectOption(7);
                    Request.countriesInAContinent(Constants.ALL_COUNTRIES_IN_CONTINENT_DESC,con, option);
                    break;
                }
                default: System.out.println("Incorrect option picked");
            }
        }


        }




