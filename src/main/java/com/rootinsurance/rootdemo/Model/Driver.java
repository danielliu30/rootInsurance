package com.rootinsurance.rootdemo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Driver {
    private UUID driverId;
    private String name;
    private List<Trip> trips;
    private double totalTimeTraveled;
    private double totalDistance;

    public Driver(String name){
        this.name = name;
        trips = new ArrayList<>();
        driverId = UUID.randomUUID();
    }

    public void addTrip(Trip trip){
        trips.add(trip);
        totalTimeTraveled += trip.getTripLength();
        totalDistance += trip.getDistance();
    }

    public List<Trip> getTrips(){
        return trips;
    }

    public String getName(){
        return this.name;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public UUID getUUID(){
        return this.driverId;
    }
    
    @Override
    public String toString(){
        return this.name + ": " + (int)Math.round(totalDistance) + " miles @ " + (int)Math.round(totalDistance/(totalTimeTraveled/1000)) + " mph";
    }
}
