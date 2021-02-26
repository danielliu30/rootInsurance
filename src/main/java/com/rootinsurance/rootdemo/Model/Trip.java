package com.rootinsurance.rootdemo.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    private Date startTime;
    private Date endTime;
    private double distance;
    private double speed;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    //time is in milliseconds
    private Trip(TripBuilder tripBuilder) throws ParseException {
        this.startTime = format.parse(tripBuilder.startTime);
        this.endTime = format.parse(tripBuilder.endTime);
        this.distance = Double.parseDouble(tripBuilder.distance);
        this.speed = (this.distance*60*60)/((this.endTime.getTime() - this.startTime.getTime())/(1000)); 
    }

    public Date getStartTime(){
        return this.startTime;
    }

    public Date getEndTime(){
        return this.endTime;
    }

    public double getDistance(){
        return this.distance;
    }

    public double getTripLength(){
        return (this.endTime.getTime() - this.startTime.getTime())/(60*60);
    }

    public double getSpeed(){
        return this.speed;
    }
    public static class TripBuilder{
        private String startTime;
        private String endTime;
        private String distance;

        public TripBuilder setStartTime(String startTime){
            this.startTime = startTime;
            return this;
        }

        public TripBuilder setEndTime(String endTime){
            this.endTime = endTime;
            return this;
        }

        public TripBuilder setDistance(String distance){
            this.distance = distance;
            return this;
        }

        public Trip build() throws ParseException {
            return new Trip(this);
        }
    }
}
