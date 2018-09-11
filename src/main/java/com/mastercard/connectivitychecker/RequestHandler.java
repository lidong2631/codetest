package com.mastercard.connectivitychecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handle incoming request and internally call checker's isConnected() method.
 */

@RestController
public class RequestHandler {

    @Autowired
    private Checker checker;

    // Just a test API to see the app is running
    @RequestMapping("/test")
    public String Test() {
        return "Ok";
    }

    @RequestMapping(value = "connected", method = RequestMethod.GET)
    public String isConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        if (checker.isConnected(origin, destination)) {
            return "yes";
        } else {
            return "no";
        }
    }
}
