package com.googlecode.mgwt.examples.showcase.client.model;

public class TrainPosition {

    private String trainStatus;
    private double trainLatitude;
    private String trainCode;
    private double trainLongitude;

    public TrainPosition(String trainStatus, double trainLatitude, double trainLongitude, String trainCode) {
        this.trainStatus = trainStatus;
        this.trainLatitude = trainLatitude;
        this.trainLongitude = trainLongitude;
        this.trainCode = trainCode;
    }

    public String getTrainStatus() {
        return trainStatus;
    }

    public double getTrainLatitude() {
        return trainLatitude;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public double getTrainLongitude() {
        return trainLongitude;
    }
}
