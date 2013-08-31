package com.googlecode.mgwt.examples.showcase.client.model;


public class TrainInfo {

    private TrainPosition trainPosition;
    private String destination;
    private String direction;
    private int dueIn;

    public TrainPosition getTrainPosition() {
        return trainPosition;
    }

    public void setTrainPosition(TrainPosition trainPosition) {
        this.trainPosition = trainPosition;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDueIn() {
        return dueIn;
    }

    public void setDueIn(int dueIn) {
        this.dueIn = dueIn;
    }
}
