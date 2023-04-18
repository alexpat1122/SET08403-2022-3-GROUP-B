package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SinglePopulationFor {

    private static final String directoryPath = Constants.OTHER_REPORTS_DIRECTORY + "SingleReports/";

    public static void singlePopulationsFor(java.sql.Connection con, String continent, String region, String country,
                                            String district, String city) throws IOException {
        Files.createDirectories(Paths.get(directoryPath));
        HashMap<String, String> result = Query.allSingleQueries(continent, region,country,district,city);
        for (Map.Entry<String,String> query: result.entrySet()) {
            int pop = ResponseFromDB.pop(con, query.getValue());
            String toFile = query.getKey() + ": " + pop;
            FileManager.writeToFile(directoryPath + query.getKey() + ".txt",toFile, true);
        }
    }

}
