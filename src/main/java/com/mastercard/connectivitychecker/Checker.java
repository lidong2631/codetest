package com.mastercard.connectivitychecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * This class perform the logic to check if two city are connected.
 */
@Component
public class Checker {

    private Map<String, List<String>> map;

    @Autowired
    private FileReader fileReader;

    public Checker(FileReader fileReader) {
        this.fileReader = fileReader;
        this.fileReader.setFilename("city.txt");
        map = fileReader.getMapFromFile();  // When initialized call getMapFromFile() to read file and store into map.
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }

    // Use BFS to find if two vertex in the graph is connected
    public boolean isConnected(String origin, String destination) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(origin);
        queue.add(origin);

        while (queue.size() != 0) {
            String currCity = queue.poll();
            if (map.containsKey(currCity)) {
                List<String> connectedCities = map.get(currCity);
                for (String connectedCity : connectedCities) {
                    if (connectedCity.equals(destination))
                        return true;
                    if (!visited.contains(connectedCity)) {
                        visited.add(connectedCity);
                        queue.add(connectedCity);
                    }
                }
            }
        }
        return false;
    }
}
