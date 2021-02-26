package com.rootinsurance.rootdemo.ServiceUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Map.Entry;
import com.rootinsurance.rootdemo.Model.Driver;
import com.rootinsurance.rootdemo.ServiceManagement.DriverTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ParseFile {

    private static DriverTrip driverTrip;

    @Autowired
    private ParseFile(DriverTrip driverTrip) {
        ParseFile.driverTrip = driverTrip;
    }

    public static void readFile(String fileName) throws ParseException, IOException {
        BufferedReader buffReader = new BufferedReader(new FileReader(new File(fileName)));
        String line;

        while ((line = buffReader.readLine()) != null) {
            String[] currLine = line.split(" ");
            String driverOrTrip = currLine[0];
            if (driverOrTrip.equals("Driver")) {
                if(!driverTrip.createDriver(currLine[1])){
                    buffReader.close();
                    throw new IllegalStateException("Cannot create Driver");
                }
            } else if (driverOrTrip.equals("Trip")) {
                if(!driverTrip.createTrip(currLine)){
                    buffReader.close();
                    throw new IllegalStateException("Driver may not exist or illFormated Trip");
                } 
            } else {
                buffReader.close();
                throw new IllegalArgumentException();
            }
        }
        

    }

    public static void printAverageTrips() {
        driverTrip.getDriverList().entrySet().stream().sorted(new Comparator<Entry<String, Driver>>() {
            @Override
            public int compare(Entry<String, Driver> o1, Entry<String, Driver> o2) {
                return (int) (o2.getValue().getTotalDistance() - o1.getValue().getTotalDistance());
            }
        }).forEach(driver -> System.out.println(driver.getValue()));
    }
}
