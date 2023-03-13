package com.napier.sem;

import com.napier.sem.structs.Country;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static boolean createFile (String filename) {
        try {
            File record = new File(filename);
            if(record.createNewFile()) {
                System.out.println("New file created " + filename);
                return true;
            }
            else {
                System.out.println("File already exists");
                return false;
            }
        }
       catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeCountriesToFile(String fileName, ArrayList<String> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String entry: entries) {
                writer.write(String.valueOf(entry));
                writer.newLine();
            }
            System.out.println("Successfully written all Countries to: " + fileName );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

