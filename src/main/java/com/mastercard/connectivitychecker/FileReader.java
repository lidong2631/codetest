package com.mastercard.connectivitychecker;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for reading the txt file by filename and setup the map used by Checker.
 */

@Component
public class FileReader {

    private String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Read from city.txt file and store info into a map
    public Map<String, List<String>> getMapFromFile() {

        Map<String, List<String>> map = new HashMap<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(this.filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = in.readLine()) != null) {
                String[] road = line.split(",");
                String city1 = road[0].trim(), city2 = road[1].trim();

                if (!map.containsKey(city1)) {
                    map.put(city1, new ArrayList<>());
                }
                if (!map.containsKey(city2)) {
                    map.put(city2, new ArrayList<>());
                }
                map.get(city1).add(city2);
                map.get(city2).add(city1);
            }

            is.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
