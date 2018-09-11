package com.mastercard.connectivitychecker;


import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Unit Test for Checker
 * Note: For this simple app just test the logic for checker
 */
public class CheckerTest {

    private Checker checker;

    @Before
    public void setUp() {
        this.checker = new Checker(new FileReader());

        // Setup map as how we want it
        Map<String, List<String>> map = new HashMap<>();
        map.put("Boston", Arrays.asList("New York", "Newark"));
        map.put("New York", Arrays.asList("Boston"));
        map.put("Philadelphia", Arrays.asList("Newark"));
        map.put("Newark", Arrays.asList("Philadelphia", "Boston"));
        map.put("Trenton", Arrays.asList("Albany"));
        map.put("Albany", Arrays.asList("Trenton"));
        this.checker.setMap(map);
    }

    @Test
    public void WhenTwoCityAreConnected_ThenisConnectedShouldReturnTrue() {
        assertTrue(this.checker.isConnected("Boston", "Philadelphia"));
    }

    @Test
    public void WhenTwoCityAreNotConnected_ThenisConnectedShouldReturnFalse() {
        assertFalse(this.checker.isConnected("Boston", "Trenton"));
    }

    @Test
    public void WhenCityNotSpecifiedInMap_ThenisConnectedShouldReturnFalse() {
        assertFalse(this.checker.isConnected("Boston", "XYZ"));
    }
}
