package com.napier.sem.QueriesToFile;

import com.napier.sem.FileManager;
import com.napier.sem.constant.Constants;
import com.napier.sem.database.Query;
import com.napier.sem.structs.Language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/** Responsible for generating all language reports **/
public class AllLanguages {



    public  void allLanguages(Response response) {
        HashMap<String, String> languageQueries = Query.languageQueries();
        ArrayList<Language> languages = new ArrayList<>();
       long allPop = response.pop("SUM(population)", Query.ALL_POP.label);
        for (Map.Entry <String,String> language: languageQueries.entrySet()) {
            long pop = response.populationForLanguage(language.getValue());
            double percentage = ((double) pop/allPop) * 100;
            languages.add(new Language(language.getKey(), pop, percentage));
        }
        languages.sort(Collections.reverseOrder());
        ArrayList<String> result = new ArrayList<>();
        for (Language language: languages) {
            result.add(language.toString());
        }
        FileManager.writeToFile(Constants.OTHER_REPORTS_DIRECTORY + "All_Languages.txt", result);
    }

}
