package com.rootinsurance.rootdemo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;

import com.rootinsurance.rootdemo.ServiceManagement.DriverTrip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverTripTest {

    @Autowired
    DriverTrip driverTrip;

    @Test
    void createTrip_WithoutDriver() {
        String[] trip = new String[] { "Trip", "Dan", "07:15", "07:45", "17.3" };
        try {
            assertFalse(driverTrip.createTrip(trip));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createTrip_MissingName() {
        String[] trip = new String[] { "Trip", "55", "07:15", "07:45", "17.3" };
        try {
            assertFalse(driverTrip.createTrip(trip));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createTrip_MissingStartTime() {
        driverTrip.createDriver("dan");
        String[] trip = new String[] { "Trip", "dan", "", "07:45", "17.3" };
        assertThrows(ParseException.class, ()->driverTrip.createTrip(trip));

    }

    @Test
    void createTrip_MissingEndTime() {
        driverTrip.createDriver("dan");
        String[] trip = new String[] { "Trip", "dan", "07:15", "", "17.3" };
        assertThrows(ParseException.class, ()->driverTrip.createTrip(trip));

    }

    @Test
    void createTrip_illFormedEndTime() {
        driverTrip.createDriver("dan");
        String[] trip = new String[] { "Trip", "dan", "07:15", "aaaaa", "17.3" };
        assertThrows(ParseException.class, ()->driverTrip.createTrip(trip));

    }

    @Test
    void createTrip_slowerThan5mph() {
        driverTrip.createDriver("dan");
        String[] trip = new String[] { "Trip", "dan", "07:15", "08:35", "5" };
        try {
            assertFalse(driverTrip.createTrip(trip));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
