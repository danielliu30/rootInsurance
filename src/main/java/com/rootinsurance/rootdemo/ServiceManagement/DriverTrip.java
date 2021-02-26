package com.rootinsurance.rootdemo.ServiceManagement;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import com.rootinsurance.rootdemo.Model.Driver;
import com.rootinsurance.rootdemo.Model.Trip;
import org.springframework.stereotype.Service;

@Service
public class DriverTrip {
    private Map<String, Driver> driverList;

    public DriverTrip() {
        driverList = new HashMap<>();
    }

    public boolean createTrip(String[] tripInput) throws ParseException {
        String driverName = tripInput[1];
        if(!nameCheck(driverName)){
            return false;
        }
        Driver driver = driverList.get(driverName);
        if (driver != null) {
            Trip trip = new Trip.TripBuilder().setStartTime(tripInput[2]).setEndTime(tripInput[3])
                    .setDistance(tripInput[4]).build();
            if (trip.getSpeed() < 100 && trip.getSpeed() > 5) {
                driver.addTrip(trip);
            }else{
                return false;
            }
        }
        return driver != null;
    }

    public boolean createDriver(String driverName) {
        if(!nameCheck(driverName)){
            return false;
        }
        try {
            driverList.putIfAbsent(driverName, new Driver(driverName));
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    public Map<String, Driver> getDriverList() {
        return this.driverList;
    }

    private boolean nameCheck(String name){
        return name.matches("[a-zA-Z]+");
    }
}
