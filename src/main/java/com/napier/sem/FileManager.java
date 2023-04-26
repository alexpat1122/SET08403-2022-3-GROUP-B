package com.napier.sem;



/** create and manage new file **/
import java.io.*;
import java.util.ArrayList;

public class FileManager {



    public static boolean createFile(String filename) {
        try {
            File record = new File(filename);
            if (record.createNewFile()) {
                System.out.println("New file created " + filename);
                return true;
            } else {
                System.out.println("File already exists");
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeToFile(String fileName,  ArrayList<String> entries) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String entry : entries) {
                writer.write(String.valueOf(entry));
                writer.newLine();
            }
            System.out.println("Successfully written all Countries to: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String fileName, String entry, boolean single) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(entry);
            System.out.println("Successfully written all Countries to: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static  ArrayList<String> readFile(String fileName) {

        ArrayList<String> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                results.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public static boolean deleteFile(String fileName) {
        File myObj = new File(fileName);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
            return true;
        } else {
            System.out.println("Failed to delete the file.");
            return false;
        }

    }
}

